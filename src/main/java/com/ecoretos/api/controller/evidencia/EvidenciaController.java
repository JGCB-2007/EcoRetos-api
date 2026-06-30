package com.ecoretos.api.controller.evidencia;

import com.ecoretos.api.dto.EvidenciaResponse;
import com.ecoretos.api.service.ArchivoService;
import com.ecoretos.api.service.EvidenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/participaciones")
public class EvidenciaController {

    private final EvidenciaService evidenciaService;
    private final ArchivoService archivoService;

    public EvidenciaController(
            EvidenciaService evidenciaService,
            ArchivoService archivoService
    ) {
        this.evidenciaService = evidenciaService;
        this.archivoService = archivoService;
    }

    @PostMapping(value = "/{idParticipacion}/evidencia", consumes = "multipart/form-data")
    public ResponseEntity<EvidenciaResponse> registrarEvidencia(
            @PathVariable Integer idParticipacion,
            @RequestParam("imagen") MultipartFile imagen
    ) {
        String urlImagen = archivoService.guardarImagen(imagen);
        return ResponseEntity.ok(evidenciaService.registrarEvidencia(idParticipacion, urlImagen));
    }
}