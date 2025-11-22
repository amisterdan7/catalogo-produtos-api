package com.example.catalago.catalago_produtos.produto.exception;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(Long id) {
        super("Produto com ID " + id + " não encontrado");
    }
}

