package com.ecoretos.api.repository;

import com.ecoretos.api.entity.UsuarioInsignia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioInsigniaRepository extends JpaRepository<UsuarioInsignia, Integer> {

    boolean existsByUsuario_IdUsuarioAndInsignia_IdInsignia(
            Integer idUsuario,
            Integer idInsignia
    );

    List<UsuarioInsignia> findByUsuario_IdUsuario(Integer idUsuario);
}