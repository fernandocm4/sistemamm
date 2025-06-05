package com.desafiommpropostaum.backapp.controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.desafiommpropostaum.backapp.dtos.requests.MemberRequestDTO;
import com.desafiommpropostaum.backapp.dtos.requests.UpdateUserRequestDTO;
import com.desafiommpropostaum.backapp.dtos.requests.UserRequestDTO;
import com.desafiommpropostaum.backapp.dtos.responses.UserResponseDTO;
import com.desafiommpropostaum.backapp.models.User;
import com.desafiommpropostaum.backapp.security.AuthenticationService;
import com.desafiommpropostaum.backapp.services.UserService;

@RestController
@RequestMapping("members")
public class UserController {

    private final UserService userService;


    @Autowired
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;

    }
        
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_GERENTE', 'ROLE_MEMBRO')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GERENTE', 'ROLE_MEMBRO')")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/manage")
    @PreAuthorize("hasRole('ROLE_GERENTE')")
    public ResponseEntity<String> modifyUser() {
        return ResponseEntity.ok("Página do gerente");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/manage/register")
    @PreAuthorize("hasRole('ROLE_GERENTE')")
    public ResponseEntity<?> registerMember(@RequestBody UserRequestDTO userRequestDTO) {
        return authenticationService.registerMember(userRequestDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/manage/{id}")
    @PreAuthorize("hasRole('ROLE_GERENTE')")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UpdateUserRequestDTO updateUserRequestDTO, @PathVariable UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(updateUserRequestDTO, id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/manage/{id}")
    @PreAuthorize("hasRole('ROLE_GERENTE')")
    public ResponseEntity<String> excludeUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }




    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("profile/{id}")
    @PreAuthorize("hasRole('ROLE_MEMBRO')")
    public ResponseEntity<String> memberProfile(@PathVariable UUID id) {
        return ResponseEntity.ok("Página do perfil de membro");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("profile/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GERENTE', 'ROLE_MEMBRO')")
    public ResponseEntity<UserResponseDTO> updateProfile(@RequestBody MemberRequestDTO memberRequestDTO, @PathVariable UUID id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateProfile(memberRequestDTO, id));
        } catch (AccessDeniedException e) { // Catch AccessDeniedException do Spring Security
        System.err.println("Acesso negado: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
     
    } catch (IllegalArgumentException e) {
        // Para erros como UUID inválido no JWT ou outros argumentos ruins
        System.err.println("Requisição inválida: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
    } catch (RuntimeException e) { // Captura quaisquer outras RuntimeExceptions inesperadas
        System.err.println("Erro interno do servidor: " + e.getMessage());
        e.printStackTrace(); // Imprima o stack trace completo para depuração
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
    }
    }

    

    
}
