package org.example.petvission.usuario.repository;

/*
 * =========================================================
 * REPOSITORIO DE USUARIO VETERINARIO
 * ---------------------------------------------------------
 * INTERFAZ ENCARGADA DE LAS OPERACIONES CRUD
 * PARA LA ENTIDAD USUARIOVETERINARIO.
 * =========================================================
 */

import org.example.petvission.usuario.model.UsuarioVeterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioVeterinarioRepository extends JpaRepository<UsuarioVeterinario, Long> {

}