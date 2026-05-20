package com.petvission.cita.controller;


import com.petvission.cita.dto.CitaUsuarioDto;
import com.petvission.shared.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.petvission.cita.dto.ReprogramarCitaDto;
import com.petvission.cita.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.petvission.cita.dto.CitaRequestDto;
import com.petvission.cita.dto.CitaResponseDto;
import com.petvission.shared.response.ApiResponse;
import com.petvission.cita.dto.CitaUsuarioDto;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    /*
     * AGENDA GENERAL
     */
    @GetMapping("/agenda")
    public ResponseEntity<?> obtenerAgendaVeterinarios() {

        return ResponseEntity.ok(
                citaService.obtenerAgendaVeterinarios()
        );
    }

    /*
     * AGENDA MENSUAL VETERINARIO
     */
    @GetMapping("/agenda/veterinario/{idVeterinario}")
    public ResponseEntity<?> obtenerAgendaVeterinario(
            @PathVariable Long idVeterinario
    ) {

        return ResponseEntity.ok(
                citaService.obtenerAgendaMensualVeterinario(
                        idVeterinario
                )
        );
    }

    /*
     * DISPONIBILIDAD BÁSICA
     */
    @GetMapping("/disponibilidad")
    public ResponseEntity<?> obtenerDisponibilidadBasica() {

        return ResponseEntity.ok(
                citaService.obtenerDisponibilidadBasica()
        );
    }

    /*
     * CITAS POR USUARIO
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> obtenerCitasUsuario(
            @PathVariable Long idUsuario
    ) {

        return ResponseEntity.ok(
                citaService.obtenerCitasPorUsuario(
                        idUsuario
                )
        );
    }

    /*
     * CITAS DEL VETERINARIO
     */
    @GetMapping("/veterinario/{idVeterinario}")
    public ResponseEntity<?> obtenerCitasVeterinario(
            @PathVariable Long idVeterinario
    ) {

        return ResponseEntity.ok(
                citaService.obtenerCitasVeterinario(
                        idVeterinario
                )
        );
    }

    /*
     * CITAS POR FECHA
     */
    @GetMapping("/fecha")
    public ResponseEntity<?> obtenerCitasPorFecha(
            @RequestParam String fecha
    ) {

        return ResponseEntity.ok(
                citaService.obtenerCitasPorFecha(
                        java.time.LocalDate.parse(fecha)
                )
        );
    }

    /*
     * CANCELAR CITA
     */
    /*
    *     @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Cita> cancelarCita(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                citaService.cancelarCita(id)
        );
    }

    */
    // tambien se estandariza por los mismos conflictos, compatibildad con el frontend

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ApiResponse<CitaUsuarioDto>> cancelarCita(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(citaService.cancelarCita(id)));
    }
    // necesita que devuelva CitaUsuarioDto para que el frontend pueda actualizar la lista de citas
    // del usuario sin hacer una nueva consulta a la API, ya que el estado de la cita se actualiza a
    // CANCELADA y el frontend puede reflejar ese cambio directamente.
    /*
     * REPROGRAMAR CITA
     */
    // se modifica esta funcion porque el CitaController tiene inconsistencia:
    // algunos endpoints devuelven ResponseEntity<?> sin el wrapper ApiResponse,
    // y otros sí lo usan. Por ejemplo cancelarCita devuelve Cita directamente
    // en vez de ApiResponse<CitaResponseDto>.
    // Eso causa que en el frontend el manejo de respuestas sea inconsistente.
    /*
    @PatchMapping("/{id}/reprogramar")
    public ResponseEntity<Cita> reprogramarCita(
            @PathVariable Long id,
            @RequestBody ReprogramarCitaDto dto
    ) {

        return ResponseEntity.ok(
                citaService.reprogramarCita(id, dto)
        );
    }
    */
    // Esto es una estandarizacion
    @PatchMapping("/{id}/reprogramar")
    public ResponseEntity<ApiResponse<CitaUsuarioDto>> reprogramarCita(
            @PathVariable Long id,
            @RequestBody ReprogramarCitaDto dto
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(citaService.reprogramarCita(id, dto))
        );
    }
    /*
     * AGENDAR CITA
     */
    @PostMapping
    public ResponseEntity<CitaResponseDto> agendarCita(
            @Valid @RequestBody CitaRequestDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(citaService.agendarCitaDto(dto));
    }
}