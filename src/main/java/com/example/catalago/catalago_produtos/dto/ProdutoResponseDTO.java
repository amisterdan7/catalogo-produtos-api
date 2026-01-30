package com.example.catalago.catalago_produtos.dto;

import com.example.catalago.catalago_produtos.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(Long id, String nome, String descricao, BigDecimal preco) {
    public ProdutoResponseDTO(Produto p){
        this(p.getId(), p.getNome(), p.getDescricao(), p.getPreco());
    }
}

