package com.example.catalago.catalago_produtos.cliente.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cliente")
@Getter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String sobrenome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 20)
    private String celular;

    @Column(nullable = false, length = 10)
    private String cep;

    public Cliente(String nome, String sobrenome, String email, String celular, String cep) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.celular = celular;
        this.cep = cep;
    }

    public void atualizar(String nome, String sobrenome, String email, String celular, String cep) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.celular = celular;
        this.cep = cep;
    }
}
