package com.desafiommpropostaum.backapp.services;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.desafiommpropostaum.backapp.dtos.requests.MemberRequestDTO;
import com.desafiommpropostaum.backapp.dtos.requests.UpdateUserRequestDTO;
import com.desafiommpropostaum.backapp.dtos.requests.UserRequestDTO;
import com.desafiommpropostaum.backapp.dtos.responses.UserResponseDTO;
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

    public User getUser(UUID id) {
        return userRepository.findById(id).get();
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDTO::new).collect(Collectors.toList());
    }


    @Transactional
    public void deleteUser(UUID id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("usuario nao encontrado");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO, UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        if(updateUserRequestDTO.nome() != null && !updateUserRequestDTO.nome().isBlank()) {
            user.setNome(updateUserRequestDTO.nome());
        }
        if(updateUserRequestDTO.username() != null && !updateUserRequestDTO.username().isBlank()) {
            user.setUsername(updateUserRequestDTO.username());
        }
        if(updateUserRequestDTO.phone() != null && !updateUserRequestDTO.phone().isBlank()) {
            user.setPhone(updateUserRequestDTO.phone());
        }
        if(updateUserRequestDTO.setor() != null && !updateUserRequestDTO.setor().isBlank()) {
            user.setSetor(updateUserRequestDTO.setor());
        }

        User updatedUser = userRepository.save(user);

        return new UserResponseDTO(updatedUser);
    }

    @Transactional
    public UserResponseDTO updateProfile(MemberRequestDTO memberRequestDTO, UUID id) throws Exception {
        


        



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            throw new RuntimeException("Usuário não autenticado");
        }

        Object principal = authentication.getPrincipal();
        

        UUID authenticatedUserId;



        if (authentication.getPrincipal() instanceof JwtAuthenticationToken) {
            
            JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) principal;
            Jwt jwt = jwtAuthenticationToken.getToken();

            /*User autheticatedUser = (User) authentication.getPrincipal();
            authenticatedUserId = autheticatedUser.getUser_id();*/

            String userIdString = jwt.getClaim("user_id");
            
            try {
                authenticatedUserId = UUID.fromString(userIdString);
            } catch (IllegalArgumentException e){
                throw new RuntimeException("ID do usuário no token nao é válido" + e);
            }
            
        } else if (principal instanceof Jwt) {
            Jwt jwt = (Jwt) principal;
            String userIdString = jwt.getClaim("user_id");
            try {
                authenticatedUserId = UUID.fromString(userIdString);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("ID do usuário do token JWT não é válido" + e);
            }
        } else {
            throw new RuntimeException("Tipo de autenticação desconhecida");
        }

        if (!authenticatedUserId.equals(id)) {
            throw new AccessDeniedException("Não autorizado");
        }



        


        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));







        if(memberRequestDTO.message() != null && !memberRequestDTO.message().isBlank()) {
            user.setMessage(memberRequestDTO.message());
            user.setPassword(passwordEncoder.encode(memberRequestDTO.password()));
        }

        User updatedMember = userRepository.save(user);
        return new UserResponseDTO(updatedMember);
    }
}
