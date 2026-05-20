package com.petvission.atencion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petvission.atencion.model.HistorialClinico;

public interface HistorialClinicoRepository
        extends JpaRepository<HistorialClinico, Long> {

    List<HistorialClinico>
    findByMascota_IdMascotaOrderByFechaRegistroDesc(
            Long idMascota
    );
}