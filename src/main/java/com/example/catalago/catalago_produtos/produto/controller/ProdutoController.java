package com.example.catalago.catalago_produtos.produto.controller;

import com.example.catalago.catalago_produtos.produto.dto.ProdutoRequestDTO;
import com.example.catalago.catalago_produtos.produto.dto.ProdutoResponseDTO;
import com.example.catalago.catalago_produtos.produto.mapper.ProdutoMapper;
import com.example.catalago.catalago_produtos.produto.model.Produto;
import com.example.catalago.catalago_produtos.produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@PreAuthorize("hasRole('USER')")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO dto) {
        Produto produto = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProdutoMapper.toDTO(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        return ResponseEntity.ok(
                service.listar().stream().map(ProdutoMapper::toDTO).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(
                ProdutoMapper.toDTO(service.buscarPorId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {

        return ResponseEntity.ok(
                ProdutoMapper.toDTO(service.atualizar(id, dto))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
