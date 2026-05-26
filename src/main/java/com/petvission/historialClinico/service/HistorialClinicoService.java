package com.petvission.historialClinico.service;

import java.util.List;

import com.petvission.historialClinico.mapper.HistorialClinicoMapper;
import org.springframework.stereotype.Service;

import com.petvission.historialClinico.dto.HistorialClinicoRequestDto;
import com.petvission.historialClinico.dto.HistorialClinicoResponseDto;
import com.petvission.historialClinico.model.HistorialClinico;
import com.petvission.historialClinico.repository.HistorialClinicoRepository;
import com.petvission.mascota.model.Mascota;
import com.petvission.mascota.repository.MascotaRepository;
import com.petvission.shared.exception.ResourceNotFoundException;
import com.petvission.usuario.model.UsuarioVeterinario;
import com.petvission.usuario.repository.UsuarioVeterinarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistorialClinicoService {

    private final HistorialClinicoRepository historialRepository;
    private final MascotaRepository mascotaRepository;
    private final UsuarioVeterinarioRepository veterinarioRepository;

    /*
     * REGISTRAR DIAGNÓSTICO
     */
    public HistorialClinicoResponseDto registrarDiagnostico(
            Long idHistorial,
            String diagnostico
    ) {
        HistorialClinico historial = historialRepository
                .findById(idHistorial)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Historial clínico no encontrado"
                ));

        historial.setDiagnostico(diagnostico);

        return HistorialClinicoMapper.toDto(
                historialRepository.save(historial)
        );
    }

    /*
     * REGISTRAR TRATAMIENTO
     */
    public HistorialClinicoResponseDto registrarTratamiento(
            Long idHistorial,
            String tratamiento,
            String receta
    ) {
        HistorialClinico historial = historialRepository
                .findById(idHistorial)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Historial clínico no encontrado"
                ));

        historial.setTratamiento(tratamiento);
        historial.setReceta(receta);

        return HistorialClinicoMapper.toDto(
                historialRepository.save(historial)
        );
    }

    /*
     * REGISTRAR OBSERVACIÓN MÉDICA
     */
    public HistorialClinicoResponseDto registrarObservacion(
            HistorialClinicoRequestDto dto
    ) {
        Mascota mascota = mascotaRepository
                .findById(dto.getIdMascota())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Mascota no encontrada"
                ));

        UsuarioVeterinario veterinario = veterinarioRepository
                .findById(dto.getIdVeterinario())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Veterinario no encontrado"
                ));

        HistorialClinico historial = HistorialClinico.builder()
                .mascota(mascota)
                .veterinario(veterinario)
                .diagnostico(dto.getDiagnostico())
                .tratamiento(dto.getTratamiento())
                .observaciones(dto.getObservaciones())
                .peso(dto.getPeso())
                .build();

        return HistorialClinicoMapper.toDto(
                historialRepository.save(historial)
        );
    }

    /*
     * OBTENER HISTORIAL DE MASCOTA
     */
    public List<HistorialClinicoResponseDto> obtenerHistorialMascota(
            Long idMascota
    ) {
        return historialRepository
                .findByMascota_IdMascotaOrderByFechaRegistroDesc(idMascota)
                .stream()
                .map(HistorialClinicoMapper::toDto)
                .toList();
    }
}