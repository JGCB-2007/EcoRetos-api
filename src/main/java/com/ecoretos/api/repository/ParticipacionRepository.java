package com.ecoretos.api.repository;

import com.ecoretos.api.entity.Participacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ParticipacionRepository extends JpaRepository<Participacion, Integer> {

    Optional<Participacion> findByUsuario_IdUsuarioAndReto_IdReto(
            Integer idUsuario,
            Integer idReto
    );

    boolean existsByUsuario_IdUsuarioAndReto_IdReto(
            Integer idUsuario,
            Integer idReto
    );

    List<Participacion> findByUsuario_IdUsuario(Integer idUsuario);

    Long countByUsuario_IdUsuarioAndEstado(
            Integer idUsuario,
            String estado
    );

    Long countByUsuario_IdUsuarioAndEstadoAndFechaAprobacionBetween(
            Integer idUsuario,
            String estado,
            LocalDateTime inicio,
            LocalDateTime fin
    );

    @Query("""
        SELECT p.fechaAprobacion
        FROM Participacion p
        WHERE p.usuario.idUsuario = :idUsuario
        AND p.estado = 'APROBADO'
        AND p.fechaAprobacion IS NOT NULL
        ORDER BY p.fechaAprobacion DESC
    """)
    List<LocalDateTime> obtenerFechasAprobacionUsuario(
            @Param("idUsuario") Integer idUsuario
    );
}