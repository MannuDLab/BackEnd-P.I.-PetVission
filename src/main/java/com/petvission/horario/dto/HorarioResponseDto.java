package com.petvission.horario.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class HorarioResponseDto {

    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Boolean disponible;
    private Long idVeterinario;
    private String nombreVeterinario;
}