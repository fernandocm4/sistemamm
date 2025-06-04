package com.desafiommpropostaum.backapp.dtos.requests;

public record UserRequestDTO(
    String nome,
    String username,
    String password,
    String role,
    String setor,
    String message,
    String phone,
    String profile
) {
    
}
