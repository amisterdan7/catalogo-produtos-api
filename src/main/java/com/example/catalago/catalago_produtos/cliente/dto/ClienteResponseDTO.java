package com.example.catalago.catalago_produtos.cliente.dto;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String email,
        String celular,
        String cep
) {}
