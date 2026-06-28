package com.ecoretos.api.service;

import com.ecoretos.api.dto.UsuarioResponse;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse obtenerUsuario(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new UsuarioResponse(
                usuario.getIdUsuario(),
                usuario.getCif(),
                usuario.getNombreCompleto(),
                usuario.getCorreoInstitucional(),
                usuario.getRol(),
                usuario.getPuntosTotales()
        );
    }
}