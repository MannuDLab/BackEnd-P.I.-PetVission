package org.example.petvission.cita.service;

import lombok.RequiredArgsConstructor;
import org.example.petvission.cita.dto.ReprogramarCitaDto;
import org.example.petvission.cita.model.Cita;
import org.example.petvission.cita.model.EstadoCita;
import org.example.petvission.cita.repository.CitaRepository;
import org.example.petvission.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;

    /*
     * CANCELAR CITA
     */
    public Cita cancelarCita(Long idCita) {

        Cita cita = citaRepository.findById(idCita)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cita no encontrada"
                        )
                );

        /*
         * VALIDAR QUE NO ESTÉ CANCELADA
         */
        if (cita.getEstado() == EstadoCita.CANCELADA) {
            throw new IllegalStateException(
                    "La cita ya está cancelada"
            );
        }

        cita.setEstado(EstadoCita.CANCELADA);

        return citaRepository.save(cita);
    }

    /*
     * REPROGRAMAR CITA
     */
    public Cita reprogramarCita(
            Long idCita,
            ReprogramarCitaDto dto
    ) {

        Cita cita = citaRepository.findById(idCita)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cita no encontrada"
                        )
                );

        /*
         * VALIDAR QUE NO ESTÉ CANCELADA
         */
        if (cita.getEstado() == EstadoCita.CANCELADA) {
            throw new IllegalStateException(
                    "No se puede reprogramar una cita cancelada"
}
