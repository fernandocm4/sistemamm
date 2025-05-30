package com.desafiommpropostaum.backapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home")
public class HomeController {

    public HomeController() {}
    
    @GetMapping
    public ResponseEntity<String> getHome() {
        return ResponseEntity.ok("Você está na página inicial");
    }
}
