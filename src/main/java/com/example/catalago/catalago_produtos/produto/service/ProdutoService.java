package com.example.catalago.catalago_produtos.produto.service;

import com.example.catalago.catalago_produtos.produto.dto.ProdutoRequestDTO;
import com.example.catalago.catalago_produtos.produto.exception.ProdutoNotFoundException;
import com.example.catalago.catalago_produtos.produto.model.Produto;
import com.example.catalago.catalago_produtos.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto salvar(ProdutoRequestDTO dto) {
        return repository.save(
                new Produto(dto.nome(), dto.descricao(), dto.preco())
        );
    }

    public Produto atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = buscarPorId(id);
        produto.atualizar(dto.nome(), dto.descricao(), dto.preco());
        return repository.save(produto);
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ProdutoNotFoundException(id);
        }
        repository.deleteById(id);
    }
}

