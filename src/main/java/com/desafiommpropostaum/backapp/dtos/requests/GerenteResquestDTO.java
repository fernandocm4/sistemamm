package com.desafiommpropostaum.backapp.dtos.requests;

public record GerenteResquestDTO(
    String nome,
    String email,
    String telefone,
    String senha,
    String mensagem,
    String setor
) {
    
}
