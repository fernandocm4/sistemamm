package com.desafiommpropostaum.backapp.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
