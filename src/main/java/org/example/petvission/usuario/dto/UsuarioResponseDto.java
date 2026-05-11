package org.example.petvission.usuario.dto;

/*
 * =========================================================
 * DTO DE RESPUESTA PARA USUARIO
 * =========================================================
 */

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UsuarioResponseDto {

    private Long idUsuario;
    private String nombre;
    private String correo;
    private String pass;
    private String telefono;
    private String rol;

}