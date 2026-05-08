package org.example.petvission.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.example.petvission.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}