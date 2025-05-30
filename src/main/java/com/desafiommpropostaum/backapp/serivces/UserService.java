package com.desafiommpropostaum.backapp.serivces;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafiommpropostaum.backapp.dtos.requests.UserRequestDTO;
import com.desafiommpropostaum.backapp.models.User;
import com.desafiommpropostaum.backapp.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User saveUser(UserRequestDTO userRequestDTO) {
        if (userRepository.findByUsername(userRequestDTO.username()).isPresent()) {
            throw new RuntimeException("Já existe um usuário com esse username");
        }

        User user = new User();

        user.setUsername(userRequestDTO.username());
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        user.setRole("GERENTE_ROLE");

        return userRepository.save(user);
    }
}
