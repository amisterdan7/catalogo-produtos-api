package com.example.catalago.catalago_produtos.cliente.mapper;

import com.example.catalago.catalago_produtos.cliente.dto.ClienteResponseDTO;
import com.example.catalago.catalago_produtos.cliente.model.Cliente;

public class ClienteMapper {

    private ClienteMapper() {}

    public static ClienteResponseDTO toDTO(Cliente c) {
        return new ClienteResponseDTO(
                c.getId(),
                c.getNome(),
                c.getSobrenome(),
                c.getEmail(),
                c.getCelular(),
                c.getCep()
        );
    }
}
