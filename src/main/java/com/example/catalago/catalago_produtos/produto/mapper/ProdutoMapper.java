package com.example.catalago.catalago_produtos.produto.mapper;

import com.example.catalago.catalago_produtos.produto.dto.ProdutoResponseDTO;
import com.example.catalago.catalago_produtos.produto.model.Produto;

public class ProdutoMapper {

    public static ProdutoResponseDTO toDTO(Produto p) {
        return new ProdutoResponseDTO(
                p.getId(),
                p.getNome(),
                p.getDescricao(),
                p.getPreco()
        );
    }
}

