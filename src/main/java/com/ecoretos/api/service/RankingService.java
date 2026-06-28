package com.ecoretos.api.service;

import com.ecoretos.api.dto.RankingResponse;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankingService {

    private final UsuarioRepository usuarioRepository;

    public RankingService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<RankingResponse> obtenerRanking() {
        List<Usuario> usuarios = usuarioRepository
                .findByRolAndActivoTrueOrderByPuntosTotalesDescNombreCompletoAsc("ESTUDIANTE");

        List<RankingResponse> ranking = new ArrayList<>();

        int posicion = 1;

        for (Usuario usuario : usuarios) {
            ranking.add(
                    new RankingResponse(
                            posicion++,
                            usuario.getIdUsuario(),
                            usuario.getNombreCompleto(),
                            usuario.getCif(),
                            usuario.getPuntosTotales()
                    )
            );
        }

        return ranking;
    }
}