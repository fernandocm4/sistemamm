package com.desafiommpropostaum.backapp.dtos.responses;

import java.util.UUID;

import com.desafiommpropostaum.backapp.models.User;

public record UserResponseDTO(
    UUID user_id,
    String username,
    String password,
    String role
) {
    public UserResponseDTO(User user) {
        this(
            user.getUser_id(),
            user.getUsername(),
            user.getPassword(),
            user.getRole()
        );
    }
}
