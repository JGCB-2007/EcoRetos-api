package com.ecoretos.api.service;

import com.ecoretos.api.dto.LoginRequest;
import com.ecoretos.api.dto.LoginResponse;
import com.ecoretos.api.entity.Usuario;
import com.ecoretos.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByCif(request.getCif())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new RuntimeException("El usuario está inactivo");
        }

        if (!usuario.getContrasenaHash().equals(request.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return new LoginResponse(
                usuario.getIdUsuario(),
                usuario.getCif(),
                usuario.getNombreCompleto(),
                usuario.getRol(),
                "Login exitoso"
        );
    }
}