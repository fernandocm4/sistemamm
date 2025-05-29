package com.desafiommpropostaum.backapp.dtos.responses;

import java.util.UUID;
import com.desafiommpropostaum.backapp.models.Membro;

public record MembroResponseDTO(
    UUID membro_id,
    String nome,
    String email,
    String telefone,
    String mensagem,
    String setor
) {
    public MembroResponseDTO(Membro membro) {
        this(
            membro.getMembro_id(),
            membro.getNome(),
            membro.getEmail(),
            membro.getTelefone(),
            membro.getMensagem(),
            membro.getSetor()
        );
    }
}
