package com.ecoretos.api.controller;

import com.ecoretos.api.dto.EvidenciaRequest;
import com.ecoretos.api.dto.EvidenciaResponse;
import com.ecoretos.api.service.EvidenciaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participaciones")
public class EvidenciaController {

    private final EvidenciaService evidenciaService;

    public EvidenciaController(EvidenciaService evidenciaService) {
        this.evidenciaService = evidenciaService;
    }

    @PostMapping("/{idParticipacion}/evidencia")
    public ResponseEntity<EvidenciaResponse> registrarEvidencia(
            @PathVariable Integer idParticipacion,
            @Valid @RequestBody EvidenciaRequest request
    ) {
        return ResponseEntity.ok(evidenciaService.registrarEvidencia(idParticipacion, request));
    }
}