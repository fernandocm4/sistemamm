package com.desafiommpropostaum.backapp.serivces;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.desafiommpropostaum.backapp.dtos.requests.MembroRequestDTO;
import com.desafiommpropostaum.backapp.dtos.responses.MembroResponseDTO;
import com.desafiommpropostaum.backapp.models.Membro;
import com.desafiommpropostaum.backapp.repositories.MembroRepository;

import jakarta.transaction.Transactional;

@Service
public class MembroService {
    private MembroRepository membroRepository;

    public MembroService(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    @Transactional
    public Membro saveMembro(MembroRequestDTO membroRequestDTO) {
        Membro membro = new Membro();

        membro.setNome(membroRequestDTO.nome());
        membro.setEmail(membroRequestDTO.email());
        membro.setTelefone(membroRequestDTO.telefone());
        membro.setMensagem(membroRequestDTO.mensagem());
        membro.setSetor(membroRequestDTO.setor());

        return membroRepository.save(membro);
    }

    public Membro getMembro(UUID id) {
        return membroRepository.findById(id).get();
    }

    public List<MembroResponseDTO> getAllMembro() {
        return membroRepository.findAll().stream().map(MembroResponseDTO::new).toList();
    }
}
