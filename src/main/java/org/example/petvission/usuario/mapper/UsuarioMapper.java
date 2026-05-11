package org.example.petvission.usuario.mapper;

/*
 * =========================================================
 * MAPPER DE USUARIO
 * ---------------------------------------------------------
 * CONVIERTE ENTIDADES A DTO Y VICEVERSA
 * =========================================================
 */

import org.example.petvission.usuario.dto.UsuarioResponseDto;
import org.example.petvission.usuario.model.Usuario;

public class UsuarioMapper {

    /*
     * =========================================================
     * CONVERTIR USUARIO A RESPONSE DTO
     * =========================================================
     */
    public static UsuarioResponseDto toDto(Usuario usuario) {

        return UsuarioResponseDto.builder()
                .idUsuario(usuario.getId())
                .nombre(usuario.getName())
                .correo(usuario.getEmail())
                .pass(usuario.getPassword())
                .telefono(usuario.getTel())
                .rol(usuario.getRol())
                .build();

    }

}