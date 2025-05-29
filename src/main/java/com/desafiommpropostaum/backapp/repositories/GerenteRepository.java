package com.desafiommpropostaum.backapp.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiommpropostaum.backapp.models.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, UUID>{
    
}
