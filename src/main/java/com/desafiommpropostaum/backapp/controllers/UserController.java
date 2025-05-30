package com.desafiommpropostaum.backapp.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiommpropostaum.backapp.dtos.responses.UserResponseDTO;
import com.desafiommpropostaum.backapp.models.User;
import com.desafiommpropostaum.backapp.services.UserService;

@RestController
@RequestMapping("members")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
        

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_GERENTE', 'ROLE_MEMBRO')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GERENTE', 'ROLE_MEMBRO')")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }

    @GetMapping("/gerente")
    @PreAuthorize("hasRole('ROLE_GERENTE')")
    public ResponseEntity<String> getGerentPage() {
        return ResponseEntity.ok("Página do gerente");
    }

    
}
