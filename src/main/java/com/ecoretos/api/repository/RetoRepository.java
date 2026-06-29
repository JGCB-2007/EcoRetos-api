package com.ecoretos.api.repository;

import com.ecoretos.api.entity.Reto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RetoRepository extends JpaRepository<Reto, Integer> {
    List<Reto> findByActivoTrue();
    List<Reto> findByActivoTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );
    Long countByActivoTrue();
}