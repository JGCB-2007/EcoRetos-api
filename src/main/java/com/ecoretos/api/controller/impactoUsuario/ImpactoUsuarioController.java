package com.ecoretos.api.controller.impactoUsuario;

import com.ecoretos.api.dto.ImpactoUsuarioResponse;
import com.ecoretos.api.service.ImpactoUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class ImpactoUsuarioController {

    private final ImpactoUsuarioService impactoUsuarioService;

    public ImpactoUsuarioController(ImpactoUsuarioService impactoUsuarioService) {
        this.impactoUsuarioService = impactoUsuarioService;
    }

    @GetMapping("/{idUsuario}/impacto")
    public ResponseEntity<ImpactoUsuarioResponse> obtenerImpacto(
            @PathVariable Integer idUsuario
    ) {
        return ResponseEntity.ok(impactoUsuarioService.obtenerImpacto(idUsuario));
    }
}