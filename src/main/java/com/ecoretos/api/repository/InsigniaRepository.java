package com.ecoretos.api.repository;

import com.ecoretos.api.entity.Insignia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InsigniaRepository extends JpaRepository<Insignia, Integer> {

    @Query("SELECT i FROM Insignia i WHERE i.activa = true AND i.puntosMinimos <= :puntos")
    List<Insignia> buscarInsigniasDisponibles(@Param("puntos") Integer puntos);
}