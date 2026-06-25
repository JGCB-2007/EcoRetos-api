package com.ecoretos.api.controller;

import com.ecoretos.api.dto.CrearRetoRequest;
import com.ecoretos.api.dto.RetoResponse;
import com.ecoretos.api.service.RetoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/retos")
public class AdminRetoController {

    private final RetoService retoService;

    public AdminRetoController(RetoService retoService) {
        this.retoService = retoService;
    }

    @PostMapping
    public ResponseEntity<RetoResponse> crearReto(
            @Valid @RequestBody CrearRetoRequest request
    ) {
        return ResponseEntity.ok(retoService.crearRetoAdmin(request));
    }
    @PutMapping("/{idReto}")
    public ResponseEntity<RetoResponse> editarReto(
            @PathVariable Integer idReto,
            @Valid @RequestBody CrearRetoRequest request
    ) {
        return ResponseEntity.ok(retoService.editarRetoAdmin(idReto, request));
    }
    @DeleteMapping("/{idReto}")
    public ResponseEntity<RetoResponse> desactivarReto(
            @PathVariable Integer idReto
    ) {
        return ResponseEntity.ok(retoService.desactivarRetoAdmin(idReto));
    }
}