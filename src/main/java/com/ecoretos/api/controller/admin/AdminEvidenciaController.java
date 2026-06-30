package com.ecoretos.api.controller.admin;

import com.ecoretos.api.dto.EvidenciaAdminResponse;
import com.ecoretos.api.service.EvidenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecoretos.api.dto.AccionEvidenciaResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/admin/evidencias")
public class AdminEvidenciaController {

    private final EvidenciaService evidenciaService;

    public AdminEvidenciaController(EvidenciaService evidenciaService) {
        this.evidenciaService = evidenciaService;
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<EvidenciaAdminResponse>> listarPendientes() {
        return ResponseEntity.ok(evidenciaService.listarPendientes());
    }

    @PutMapping("/{idEvidencia}/aprobar")
    public ResponseEntity<AccionEvidenciaResponse> aprobar(
            @PathVariable Integer idEvidencia
    ) {
        return ResponseEntity.ok(evidenciaService.aprobarEvidencia(idEvidencia));
    }

    @PutMapping("/{idEvidencia}/rechazar")
    public ResponseEntity<AccionEvidenciaResponse> rechazar(
            @PathVariable Integer idEvidencia
    ) {
        return ResponseEntity.ok(evidenciaService.rechazarEvidencia(idEvidencia));
    }
}