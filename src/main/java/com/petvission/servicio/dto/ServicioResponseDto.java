package com.petvission.servicio.dto;

import com.petvission.servicio.model.CategoriaServicio;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicioResponseDto {

    /* Identificador único del servicio */
    private Integer idServicio;

    /* Nombre del servicio */
    private String nombre;

    /* Categoría del servicio */
    private CategoriaServicio categoria;

    /* Descripción del servicio */
    private String descripcion;

    /* Duración estimada en minutos */
    private Integer duracionMinutos;

    /* Precio del servicio */
    private Double precio;

    /* Estado de disponibilidad del servicio */
    private Boolean activo;
}
