package com.ecoretos.api.controller;

import com.ecoretos.api.dto.CrearPropuestaRequest;
import com.ecoretos.api.dto.PropuestaResponse;
import com.ecoretos.api.service.PropuestaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propuestas")
public class PropuestaController {

    private final PropuestaService propuestaService;

    public PropuestaController(PropuestaService propuestaService) {
        this.propuestaService = propuestaService;
    }

    @PostMapping
    public ResponseEntity<PropuestaResponse> crearPropuesta(
            @Valid @RequestBody CrearPropuestaRequest request
    ) {
        return ResponseEntity.ok(propuestaService.crearPropuesta(request));
    }

    @GetMapping("/mias/{idUsuario}")
    public ResponseEntity<List<PropuestaResponse>> listarMisPropuestas(
            @PathVariable Integer idUsuario
    ) {
        return ResponseEntity.ok(propuestaService.listarMisPropuestas(idUsuario));
    }
}