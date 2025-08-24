package com.example.catalago.catalago_produtos.controller;

import com.example.catalago.catalago_produtos.model.Produto;
import com.example.catalago.catalago_produtos.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public Produto createProduto(@Valid @RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> getAllProdutos(){
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto getProdutosById(@PathVariable Long id){
        return produtoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Produto updateProduto(@PathVariable Long id, @Valid @RequestBody Produto produtoDetalhes){
        Produto produto = produtoRepository.findById(id).orElse(null);
        if(produto != null){
            produto.setNome(produtoDetalhes.getNome());
            produto.setDescricao(produtoDetalhes.getDescricao());
            produto.setPreco(produtoDetalhes.getPreco());
            produtoRepository.save(produto);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id){
        produtoRepository.deleteById(id);
    }
}
