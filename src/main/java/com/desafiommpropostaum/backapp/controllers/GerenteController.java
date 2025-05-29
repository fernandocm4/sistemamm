package com.desafiommpropostaum.backapp.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiommpropostaum.backapp.dtos.requests.GerenteResquestDTO;
import com.desafiommpropostaum.backapp.models.Gerente;
import com.desafiommpropostaum.backapp.serivces.GerenteService;

@RestController
@RequestMapping("gerente")
public class GerenteController {
    private final GerenteService gerenteService;

    public GerenteController(GerenteService gerenteService) {this.gerenteService = gerenteService;}

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Gerente> getGerente(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(gerenteService.getGerente(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Gerente> saveGerente(@RequestBody GerenteResquestDTO gerenteResquestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gerenteService.saveGerente(gerenteResquestDTO));
    }
}
