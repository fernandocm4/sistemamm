package com.desafiommpropostaum.backapp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiommpropostaum.backapp.dtos.responses.UserResponseDTO;
import com.desafiommpropostaum.backapp.repositories.UserRepository;

@RestController
@RequestMapping("members")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
        


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_GERENTE', 'ROLE_MEMBRO')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userRepository.findAll().stream().map(UserResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/gerente")
    @PreAuthorize("hasRole('ROLE_GERENTE')")
    public ResponseEntity<String> getGerentPage() {
        return ResponseEntity.ok("Página do gerente");
    }

    
}
