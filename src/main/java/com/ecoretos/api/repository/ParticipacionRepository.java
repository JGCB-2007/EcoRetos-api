package com.ecoretos.api.repository;

import com.ecoretos.api.entity.Participacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface ParticipacionRepository extends JpaRepository<Participacion, Integer> {

    Optional<Participacion> findByUsuario_IdUsuarioAndReto_IdReto(
            Integer idUsuario,
            Integer idReto
    );

    boolean existsByUsuario_IdUsuarioAndReto_IdReto(Integer idUsuario, Integer idReto);

    List<Participacion> findByUsuario_IdUsuario(Integer idUsuario);
}