package com.desafiommpropostaum.backapp.dtos.requests;


public record UpdateUserRequestDTO(
    String nome,
    String username,
    String phone,
    String setor
) {
    
}
