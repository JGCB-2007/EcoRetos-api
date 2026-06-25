package com.ecoretos.api.repository;

import com.ecoretos.api.entity.Insignia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsigniaRepository extends JpaRepository<Insignia, Integer> {
    List<Insignia> findByActivaTrueOrderByPuntosMinimosAsc();
}