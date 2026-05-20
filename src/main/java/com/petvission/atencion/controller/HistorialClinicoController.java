package com.petvission.atencion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petvission.atencion.dto.HistorialClinicoRequestDto;
import com.petvission.atencion.service.HistorialClinicoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor
public class HistorialClinicoController {

    private final HistorialClinicoService historialService;

    /*
     * REGISTRAR OBSERVACIÓN MÉDICA
     */
    @PostMapping
    public ResponseEntity<?> registrarObservacion(
            @RequestBody HistorialClinicoRequestDto dto
    ) {

        return ResponseEntity.ok(
                historialService.registrarObservacion(dto)
        );
    }

    /*
     * OBTENER HISTORIAL DE MASCOTA
     */
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<?> obtenerHistorialMascota(
            @PathVariable Long idMascota
    ) {

        return ResponseEntity.ok(
                historialService.obtenerHistorialMascota(
                        idMascota
                )
        );
    }
}