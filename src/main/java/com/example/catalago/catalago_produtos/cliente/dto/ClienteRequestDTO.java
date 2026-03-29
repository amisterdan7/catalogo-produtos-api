package com.example.catalago.catalago_produtos.cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteRequestDTO(
        @NotBlank String nome,
        @NotBlank String sobrenome,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "^\\d{10,11}$", message = "Celular deve conter 10 ou 11 dígitos") String celular,
        @NotBlank @Pattern(regexp = "^\\d{8}$", message = "CEP deve conter 8 dígitos") String cep
) {}
