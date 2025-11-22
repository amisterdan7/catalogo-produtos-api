package com.example.catalago.catalago_produtos.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ProdutoRequestDTO(
        @NotBlank String nome,
        @NotBlank String descricao,
        @Positive BigDecimal preco
) {}

