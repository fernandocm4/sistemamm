package com.desafiommpropostaum.backapp.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiommpropostaum.backapp.models.User;

public interface UserRepository extends JpaRepository<User, UUID>{

    Optional<User> findByUsername(String username);
    
}
