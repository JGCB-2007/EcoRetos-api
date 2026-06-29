package com.ecoretos.api.service;

import com.ecoretos.api.dto.AdminDashboardResponse;
import com.ecoretos.api.repository.EvidenciaRepository;
import com.ecoretos.api.repository.InsigniaRepository;
import com.ecoretos.api.repository.RetoPropuestoRepository;
import com.ecoretos.api.repository.RetoRepository;
import com.ecoretos.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardService {

    private final RetoRepository retoRepository;
    private final EvidenciaRepository evidenciaRepository;
    private final InsigniaRepository insigniaRepository;
    private final UsuarioRepository usuarioRepository;
    private final RetoPropuestoRepository retoPropuestoRepository;

    public AdminDashboardService(
            RetoRepository retoRepository,
            EvidenciaRepository evidenciaRepository,
            InsigniaRepository insigniaRepository,
            UsuarioRepository usuarioRepository,
            RetoPropuestoRepository retoPropuestoRepository
    ) {
        this.retoRepository = retoRepository;
        this.evidenciaRepository = evidenciaRepository;
        this.insigniaRepository = insigniaRepository;
        this.usuarioRepository = usuarioRepository;
        this.retoPropuestoRepository = retoPropuestoRepository;
    }

    public AdminDashboardResponse obtenerDashboard() {
        return new AdminDashboardResponse(
                retoRepository.countByActivoTrue(),
                evidenciaRepository.countByEstadoValidacion("PENDIENTE"),
                insigniaRepository.countByActivaTrue(),
                usuarioRepository.countByActivoTrue(),
                retoPropuestoRepository.countByEstado("PENDIENTE")
        );
    }
}