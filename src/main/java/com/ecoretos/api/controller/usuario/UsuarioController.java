package com.ecoretos.api.controller.usuario;

import com.ecoretos.api.dto.UsuarioResponse;
import com.ecoretos.api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponse> obtenerUsuario(
            @PathVariable Integer idUsuario
    ) {
        return ResponseEntity.ok(usuarioService.obtenerUsuario(idUsuario));
    }
}