package com.petvission.horario.mapper;

import com.petvission.horario.dto.HorarioResponseDto;
import com.petvission.horario.model.Horario;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapper {

    public HorarioResponseDto toDto(Horario horario) {
        return HorarioResponseDto.builder()
                .id(horario.getId())
                .fecha(horario.getFecha())
                .hora(horario.getHora())
                .disponible(horario.getDisponible())
                .idVeterinario(horario.getVeterinario().getIdUsuario())
                .nombreVeterinario(
                        horario.getVeterinario().getUsuario().getNombres()
                                + " " +
                                horario.getVeterinario().getUsuario().getApellidos()
                )
                .build();
    }
}