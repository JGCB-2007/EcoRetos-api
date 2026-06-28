package com.ecoretos.api.controller;

import com.ecoretos.api.dto.RetoResponse;
import com.ecoretos.api.service.RetoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecoretos.api.dto.AceptarRetoRequest;
import com.ecoretos.api.dto.ParticipacionResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/retos")
public class RetoController {

    private final RetoService retoService;

    public RetoController(RetoService retoService) {
        this.retoService = retoService;
    }

    @GetMapping
    public ResponseEntity<List<RetoResponse>> listarRetos() {
        return ResponseEntity.ok(retoService.listarRetosActivos());
    }
    @PostMapping("/{idReto}/aceptar")
    public ResponseEntity<ParticipacionResponse> aceptarReto(
            @PathVariable Integer idReto,
            @Valid @RequestBody AceptarRetoRequest request
    ) {
        return ResponseEntity.ok(retoService.aceptarReto(idReto, request.getIdUsuario()));
    }
    @DeleteMapping("/{idReto}/cancelar/{idUsuario}")
    public ResponseEntity<Map<String, String>> cancelarReto(
            @PathVariable Integer idReto,
            @PathVariable Integer idUsuario
    ) {
        String mensaje = retoService.cancelarReto(idReto, idUsuario);
        return ResponseEntity.ok(Map.of("mensaje", mensaje));
    }
}