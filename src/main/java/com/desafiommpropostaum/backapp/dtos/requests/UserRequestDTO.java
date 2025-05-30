package com.desafiommpropostaum.backapp.dtos.requests;

public record UserRequestDTO(
    String username,
    String password,
    String role
) {
    
}
