package com.desafiommpropostaum.backapp.controllers;

import java.util.List;
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

import com.desafiommpropostaum.backapp.dtos.requests.MembroRequestDTO;
import com.desafiommpropostaum.backapp.dtos.responses.MembroResponseDTO;
import com.desafiommpropostaum.backapp.models.Membro;
import com.desafiommpropostaum.backapp.serivces.MembroService;

@RestController
@RequestMapping("membros")
public class MembroController {
    private final MembroService membroService;

    public MembroController(MembroService membroService) {this.membroService = membroService;}

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Membro> getMembro(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(membroService.getMembro(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<MembroResponseDTO>> getAllMembro() {
        return ResponseEntity.status(HttpStatus.OK).body(membroService.getAllMembro());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Membro> saveMembro(@RequestBody MembroRequestDTO membroRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(membroService.saveMembro(membroRequestDTO));
    }
}
