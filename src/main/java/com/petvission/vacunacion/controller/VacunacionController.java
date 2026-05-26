package com.petvission.vacunacion.controller;

import com.petvission.shared.response.ApiResponse;
import com.petvission.vacunacion.dto.VacunacionRequestDto;
import com.petvission.vacunacion.dto.VacunacionResponseDto;
import com.petvission.vacunacion.service.VacunacionService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vacunacion")
@RequiredArgsConstructor
public class VacunacionController {

    private final VacunacionService vacunacionService;

    /*
     * REGISTRAR VACUNACIÓN
     */
    @PostMapping
    public ResponseEntity<ApiResponse<VacunacionResponseDto>> registrarVacunacion(
            @RequestBody VacunacionRequestDto dto
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(vacunacionService.registrarVacunacion(dto))
        );
    }
}