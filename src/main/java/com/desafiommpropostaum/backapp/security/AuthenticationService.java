package com.desafiommpropostaum.backapp.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafiommpropostaum.backapp.dtos.requests.UserRequestDTO;
import com.desafiommpropostaum.backapp.models.User;
import com.desafiommpropostaum.backapp.repositories.UserRepository;

@Service
public class AuthenticationService {
    
    private final JtwService jtwService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(JtwService jtwService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jtwService = jtwService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(Authentication authentication) {
        return jtwService.generateToken(authentication);
    }


    @Transactional
    public ResponseEntity<?> registerUser(UserRequestDTO userRequestDTO) {
        if (userRepository.findByUsername(userRequestDTO.username()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exist");
        }

        User user = new User();
        user.setUsername(userRequestDTO.username());
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));

        String roleName;
        if ("GERENTE".equalsIgnoreCase(userRequestDTO.role())) {
            roleName = "ROLE_GERENTE";
        } else if ("MEMBRO".equalsIgnoreCase(userRequestDTO.role())) {
            roleName = "ROLE_MEMBRO";
        } else {
            return ResponseEntity.badRequest().body("Tipo de usuário inválido. Use 'GERENTE' ou 'MEMBRO'.");
        }

        user.setRole(roleName);



        userRepository.save(user);
        return ResponseEntity.ok("Usuário " + userRequestDTO.username() + " registrado com sucesso como " + userRequestDTO.role() + "!");

    }
}
