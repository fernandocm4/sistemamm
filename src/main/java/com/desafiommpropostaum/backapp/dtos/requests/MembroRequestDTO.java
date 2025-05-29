package com.desafiommpropostaum.backapp.dtos.requests;

public record MembroRequestDTO(
    String nome,
    String email,
    String telefone,
    String mensagem
) {
    
}
