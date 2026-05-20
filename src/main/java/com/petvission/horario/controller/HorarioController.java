package com.petvission.horario.controller;

import com.petvission.horario.dto.HorarioRequestDto;
import com.petvission.horario.dto.HorarioResponseDto;
import com.petvission.horario.service.HorarioService;
import com.petvission.shared.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
public class HorarioController {

    private final HorarioService horarioService;

    /*
     * LISTAR TODOS LOS HORARIOS
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<HorarioResponseDto>>> listarHorarios() {
        return ResponseEntity.ok(
                ApiResponse.success(horarioService.obtenerHorarios())
        );
    }

    /*
     * HORARIOS POR VETERINARIO
     */
    @GetMapping("/veterinario/{idVeterinario}")
    public ResponseEntity<ApiResponse<List<HorarioResponseDto>>> obtenerPorVeterinario(
            @PathVariable Long idVeterinario
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(horarioService.obtenerPorVeterinario(idVeterinario))
        );
    }

    /*
     * HORARIOS DISPONIBLES POR VETERINARIO
     */
    @GetMapping("/veterinario/{idVeterinario}/disponibles")
    public ResponseEntity<ApiResponse<List<HorarioResponseDto>>> obtenerDisponibles(
            @PathVariable Long idVeterinario
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(horarioService.obtenerDisponiblesPorVeterinario(idVeterinario))
        );
    }

    /*
     * CREAR HORARIO
     */
    @PostMapping
    public ResponseEntity<ApiResponse<HorarioResponseDto>> crearHorario(
            @Valid @RequestBody HorarioRequestDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(horarioService.guardarHorario(dto))
        );
    }

    /*
     * DESACTIVAR HORARIO
     */
    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<ApiResponse<HorarioResponseDto>> desactivarHorario(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(horarioService.desactivarHorario(id))
        );
    }
}