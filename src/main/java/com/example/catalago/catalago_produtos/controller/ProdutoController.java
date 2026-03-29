package com.example.catalago.catalago_produtos.controller;

import com.example.catalago.catalago_produtos.dto.ProdutoRequestDTO;
import com.example.catalago.catalago_produtos.dto.ProdutoResponseDTO;
import com.example.catalago.catalago_produtos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> create(@Valid @RequestBody ProdutoRequestDTO dto){
        return ResponseEntity.ok(new ProdutoResponseDTO(service.save(dto)));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> getAll(){
        return ResponseEntity.ok(service.findAll().stream().map(ProdutoResponseDTO::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(new ProdutoResponseDTO(service.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO dto){
        return ResponseEntity.ok(new ProdutoResponseDTO(service.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok("Produto removido com sucesso!");
    }
}
