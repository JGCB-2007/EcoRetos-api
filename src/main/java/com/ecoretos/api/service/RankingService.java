package com.ecoretos.api.service;

import com.ecoretos.api.dto.RankingResponse;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    private final UsuarioRepository usuarioRepository;

    public RankingService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<RankingResponse> obtenerRanking() {
        return usuarioRepository
                .findByRolAndActivoTrueOrderByPuntosTotalesDesc("ESTUDIANTE")
                .stream()
                .map(usuario -> new RankingResponse(
                        usuario.getIdUsuario(),
                        usuario.getNombreCompleto(),
                        usuario.getCif(),
                        usuario.getPuntosTotales()
                ))
                .toList();
    }
}