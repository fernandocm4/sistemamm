package com.desafiommpropostaum.backapp.dtos.responses;

import java.util.UUID;
import com.desafiommpropostaum.backapp.models.Gerente;

public record GerenteResponseDTO(
    UUID gerente_id,
    String nome,
    String email,
    String telefone,
    String senha,
    String mensagem
) {
    public GerenteResponseDTO(Gerente gerente) {
        this(
            gerente.getGerente_id(),
            gerente.getNome(),
            gerente.getEmail(),
            gerente.getTelefone(),
            gerente.getSenha(),
            gerente.getMensagem()
        );
    }
}
