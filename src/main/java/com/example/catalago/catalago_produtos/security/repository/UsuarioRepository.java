package com.example.catalago.catalago_produtos.security.repository;

import com.example.catalago.catalago_produtos.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
