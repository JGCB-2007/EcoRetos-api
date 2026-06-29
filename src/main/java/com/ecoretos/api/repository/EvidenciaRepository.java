package com.ecoretos.api.repository;

import com.ecoretos.api.entity.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EvidenciaRepository extends JpaRepository<Evidencia, Integer> {
    boolean existsByParticipacion_IdParticipacion(Integer idParticipacion);
    List<Evidencia> findByEstadoValidacion(String estado);
    Long countByEstadoValidacion(String estadoValidacion);
}