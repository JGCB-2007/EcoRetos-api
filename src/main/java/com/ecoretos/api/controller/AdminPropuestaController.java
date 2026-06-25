package com.ecoretos.api.controller;

import com.ecoretos.api.dto.PropuestaResponse;
import com.ecoretos.api.service.PropuestaRevisionService;
import com.ecoretos.api.service.PropuestaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecoretos.api.dto.RevisionPropuestaRequest;

import java.util.List;

@RestController
@RequestMapping("/admin/propuestas")
public class AdminPropuestaController {

    private final PropuestaService propuestaService;
    private final PropuestaRevisionService revisionService;

    public AdminPropuestaController(
            PropuestaService propuestaService,
            PropuestaRevisionService revisionService
    ) {
        this.propuestaService = propuestaService;
        this.revisionService = revisionService;
    }

    @GetMapping
    public ResponseEntity<List<PropuestaResponse>> listarPendientes() {
        return ResponseEntity.ok(propuestaService.listarPendientes());
    }
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<PropuestaResponse> aprobar(
            @PathVariable Integer id,
            @RequestBody RevisionPropuestaRequest request
    ) {
        return ResponseEntity.ok(revisionService.aprobar(id, request));
    }

    @PutMapping("/{id}/rechazar")
    public ResponseEntity<PropuestaResponse> rechazar(
            @PathVariable Integer id,
            @RequestBody RevisionPropuestaRequest request
    ) {
        return ResponseEntity.ok(revisionService.rechazar(id, request));
    }
}