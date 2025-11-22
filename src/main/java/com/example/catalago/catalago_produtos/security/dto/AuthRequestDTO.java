package com.example.catalago.catalago_produtos.security.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(

        @NotBlank(message = "Username é obrigatório")
        String username,

        @NotBlank(message = "Senha é obrigatória")
        String password

) {}
