package com.desafiommpropostaum.backapp.serivces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.desafiommpropostaum.backapp.dtos.requests.GerenteResquestDTO;
import com.desafiommpropostaum.backapp.models.Gerente;
import com.desafiommpropostaum.backapp.repositories.GerenteRepository;

import jakarta.transaction.Transactional;

@Service
public class GerenteService {
    private GerenteRepository gerenteRepository;

    public GerenteService(GerenteRepository gerenteRepository) {
        this.gerenteRepository = gerenteRepository;
    }

    @Transactional
    public Gerente saveGerente(GerenteResquestDTO gerenteResquestDTO) {
        Gerente gerente = new Gerente();

        gerente.setNome(gerenteResquestDTO.nome());
        gerente.setEmail(gerenteResquestDTO.email());
        gerente.setTelefone(gerenteResquestDTO.telefone());
        gerente.setSenha(gerenteResquestDTO.senha());
        gerente.setMensagem(gerenteResquestDTO.mensagem());

        return gerenteRepository.save(gerente);
    }

    public Gerente getGerente(UUID id) {
        return gerenteRepository.findById(id).get();
    }
}
