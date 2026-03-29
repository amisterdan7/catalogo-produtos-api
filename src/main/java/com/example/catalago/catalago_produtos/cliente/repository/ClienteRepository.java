package com.example.catalago.catalago_produtos.cliente.repository;

import com.example.catalago.catalago_produtos.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
