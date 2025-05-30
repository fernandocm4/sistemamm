package com.desafiommpropostaum.backapp.dtos.responses;

import java.util.UUID;

import com.desafiommpropostaum.backapp.models.User;

public record UserResponseDTO(
    UUID user_id,
    String nome,
    String username,
    String password,
    String role,
    String setor,
    String message,
    String phone
) {
    public UserResponseDTO(User user) {
        this(
            user.getUser_id(),
            user.getNome(),
            user.getUsername(),
            user.getPassword(),
            user.getRole(),
            user.getSetor(),
            user.getMessage(),
            user.getPhone()
        );
    }
}
