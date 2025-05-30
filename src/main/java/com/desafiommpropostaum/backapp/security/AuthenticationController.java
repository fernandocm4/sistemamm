package com.desafiommpropostaum.backapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafiommpropostaum.backapp.dtos.requests.UserRequestDTO;


@RestController
public class AuthenticationController {
    
    @Autowired
    private final AuthenticationService authenticationService;

    public AuthenticationController(
        AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("authenticate")
    public ResponseEntity<String> authenticate(Authentication authentication) {
        return ResponseEntity.ok(authenticationService.authenticate(authentication));
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        return authenticationService.registerUser(userRequestDTO);
    }
}
