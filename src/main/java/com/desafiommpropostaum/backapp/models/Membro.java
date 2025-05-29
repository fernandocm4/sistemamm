package com.desafiommpropostaum.backapp.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "membro")
@Table(name= "membro")
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "membro_id", nullable = false, updatable = false, unique = true)
    private UUID membro_id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telefone", nullable = false, unique = true)
    private String telefone;

    @Column(name = "mensagem", nullable = true)
    private String mensagem;
}
