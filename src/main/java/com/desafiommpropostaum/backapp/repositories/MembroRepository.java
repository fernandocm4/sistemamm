package com.desafiommpropostaum.backapp.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiommpropostaum.backapp.models.Membro;

public interface MembroRepository extends JpaRepository<Membro, UUID>{
    
}
