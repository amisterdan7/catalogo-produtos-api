package com.example.catalago.catalago_produtos.produto.dto;

import com.example.catalago.catalago_produtos.produto.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(Long id, String nome, String descricao, BigDecimal preco) {
    public ProdutoResponseDTO(Produto p){
        this(p.getId(), p.getNome(), p.getDescricao(), p.getPreco());
    }
}

