package com.example.catalago.catalago_produtos.service;

import com.example.catalago.catalago_produtos.dto.ProdutoRequestDTO;
import com.example.catalago.catalago_produtos.model.Produto;
import com.example.catalago.catalago_produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class ProdutoService {


    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }
    public Produto save(ProdutoRequestDTO dto) {
        Produto produto = new Produto(dto.nome(), dto.descricao(), dto.preco());
        return repository.save(produto);
    }

    public Produto update(Long id, ProdutoRequestDTO dto) {
        Produto produto = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        return repository.save(produto);
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }


    public Produto findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }


    public void deleteById(Long id){
        repository.deleteById(id);
    }




}
