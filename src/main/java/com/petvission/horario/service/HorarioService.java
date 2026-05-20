package com.petvission.horario.service;

import com.petvission.horario.dto.HorarioRequestDto;
import com.petvission.horario.dto.HorarioResponseDto;
import com.petvission.horario.mapper.HorarioMapper;
import com.petvission.horario.model.Horario;
import com.petvission.horario.repository.HorarioRepository;
import com.petvission.shared.exception.ResourceNotFoundException;
import com.petvission.usuario.model.UsuarioVeterinario;
import com.petvission.usuario.repository.UsuarioVeterinarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioService {

    private final HorarioRepository horarioRepository;
    private final UsuarioVeterinarioRepository veterinarioRepository;
    private final HorarioMapper horarioMapper;

    /*
     * LISTAR TODOS LOS HORARIOS
     */
    public List<HorarioResponseDto> obtenerHorarios() {
        return horarioRepository.findAll()
                .stream()
                .map(horarioMapper::toDto)
                .toList();
    }

    /*
     * HORARIOS POR VETERINARIO
     */
    public List<HorarioResponseDto> obtenerPorVeterinario(Long idVeterinario) {
        return horarioRepository.findByVeterinario_IdUsuario(idVeterinario)
                .stream()
                .map(horarioMapper::toDto)
                .toList();
    }

    /*
     * HORARIOS DISPONIBLES POR VETERINARIO
     */
    public List<HorarioResponseDto> obtenerDisponiblesPorVeterinario(Long idVeterinario) {
        return horarioRepository.findByVeterinario_IdUsuarioAndDisponibleTrue(idVeterinario)
                .stream()
                .map(horarioMapper::toDto)
                .toList();
    }

    /*
     * CREAR HORARIO
     */
    public HorarioResponseDto guardarHorario(HorarioRequestDto dto) {
        UsuarioVeterinario veterinario = veterinarioRepository.findById(dto.getIdVeterinario())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado"));

        Horario horario = Horario.builder()
                .fecha(dto.getFecha())
                .hora(dto.getHora())
                .veterinario(veterinario)
                .disponible(true)
                .build();

        return horarioMapper.toDto(horarioRepository.save(horario));
    }

    /*
     * DESACTIVAR HORARIO
     * Se llama al agendar una cita para marcar el slot como no disponible
     */
    public HorarioResponseDto desactivarHorario(Long id) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario no encontrado"));

        horario.setDisponible(false);
        return horarioMapper.toDto(horarioRepository.save(horario));
    }
}