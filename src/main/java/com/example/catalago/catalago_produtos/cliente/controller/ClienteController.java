package com.example.catalago.catalago_produtos.cliente.controller;

import com.example.catalago.catalago_produtos.cliente.dto.ClienteRequestDTO;
import com.example.catalago.catalago_produtos.cliente.dto.ClienteResponseDTO;
import com.example.catalago.catalago_produtos.cliente.mapper.ClienteMapper;
import com.example.catalago.catalago_produtos.cliente.model.Cliente;
import com.example.catalago.catalago_produtos.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO dto) {
        Cliente cliente = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClienteMapper.toDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity.ok(
                service.listar().stream().map(ClienteMapper::toDTO).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(
                ClienteMapper.toDTO(service.buscarPorId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(
                ClienteMapper.toDTO(service.atualizar(id, dto))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
