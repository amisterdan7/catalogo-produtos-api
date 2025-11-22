package com.example.catalago.catalago_produtos.produto.repository;

import com.example.catalago.catalago_produtos.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

