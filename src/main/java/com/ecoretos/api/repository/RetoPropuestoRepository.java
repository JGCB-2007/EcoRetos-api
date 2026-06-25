package com.ecoretos.api.repository;

import com.ecoretos.api.entity.RetoPropuesto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetoPropuestoRepository extends JpaRepository<RetoPropuesto, Integer> {

    List<RetoPropuesto> findByEstadoOrderByFechaPropuestaDesc(String estado);

    List<RetoPropuesto> findByUsuario_IdUsuarioOrderByFechaPropuestaDesc(Integer idUsuario);
}