package org.example.petvission.usuario.service;

// SERVICIO DE USUARIO
// CONTIENE LA LÓGICA DE NEGOCIO DE LOS USUARIOS

import org.example.petvission.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    // REPOSITORIO DE USUARIO
    private final UsuarioRepository usuarioRepository;

    // CONSTRUCTOR
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

}