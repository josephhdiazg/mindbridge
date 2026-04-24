package com.usta.mindbridge.controller;

import com.usta.mindbridge.dto.request.FactorRiesgoRequest;
import com.usta.mindbridge.dto.response.FactorRiesgoResponse;
import com.usta.mindbridge.service.FactorRiesgoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/factores-riesgo")
public class FactorRiesgoController {

    private final FactorRiesgoService factorRiesgoService;

    public FactorRiesgoController(FactorRiesgoService factorRiesgoService) {
        this.factorRiesgoService = factorRiesgoService;
    }

    @GetMapping
    public ResponseEntity<List<FactorRiesgoResponse>> listar() {
        return ResponseEntity.ok(factorRiesgoService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactorRiesgoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(factorRiesgoService.obtenerPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FactorRiesgoResponse> crear(@Valid @RequestBody FactorRiesgoRequest request) {
        return ResponseEntity.status(201).body(factorRiesgoService.crear(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FactorRiesgoResponse> actualizar(
            @PathVariable Long id, @Valid @RequestBody FactorRiesgoRequest request) {
        return ResponseEntity.ok(factorRiesgoService.actualizar(id, request));
    }

    @PatchMapping("/{id}/toggle")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FactorRiesgoResponse> toggle(@PathVariable Long id) {
        return ResponseEntity.ok(factorRiesgoService.toggleActivo(id));
    }
}
