package com.example.catalago.catalago_produtos.cliente.exception;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(Long id) {
        super("Cliente não encontrado: " + id);
    }
}
