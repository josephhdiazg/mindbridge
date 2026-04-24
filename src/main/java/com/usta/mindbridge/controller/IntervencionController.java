package com.usta.mindbridge.controller;

import com.mindbridge.dto.intervencion.IntervencionDTO;
import com.mindbridge.dto.intervencion.IntervencionRequest;
import com.mindbridge.dto.intervencion.IntervencionResponse;
import com.mindbridge.service.IntervencionService;
import com.mindbridge.service.ProfesionalService;
import com.mindbridge.dto.intervencion.ProfesionalDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IntervencionController {

    private final IntervencionService intervencionService;
    private final ProfesionalService profesionalService;

    public IntervencionController(IntervencionService intervencionService,
                                  ProfesionalService profesionalService) {
        this.intervencionService = intervencionService;
        this.profesionalService = profesionalService;
    }

    @PostMapping("/api/intervenciones")
    @PreAuthorize("hasRole('PSICOLOGO')")
    public ResponseEntity<IntervencionResponse> registrar(@Valid @RequestBody IntervencionRequest request) {
        return ResponseEntity.status(201).body(intervencionService.registrar(request));
    }

    @GetMapping("/api/intervenciones/{id}")
    @PreAuthorize("hasAnyRole('PSICOLOGO', 'ADMIN')")
    public ResponseEntity<IntervencionResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(intervencionService.obtenerPorId(id));
    }

    @PutMapping("/api/intervenciones/{id}")
    @PreAuthorize("hasRole('PSICOLOGO')")
    public ResponseEntity<IntervencionResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody IntervencionRequest request) {
        return ResponseEntity.ok(intervencionService.actualizar(id, request));
    }

    @GetMapping("/api/alertas/{id}/intervenciones")
    @PreAuthorize("hasRole('PSICOLOGO')")
    public ResponseEntity<List<IntervencionDTO>> listarPorAlerta(@PathVariable Long id) {
        return ResponseEntity.ok(intervencionService.listarPorAlerta(id));
    }

    @GetMapping("/api/profesionales")
    @PreAuthorize("hasAnyRole('ADMIN', 'PSICOLOGO')")
    public ResponseEntity<List<ProfesionalDTO>> listarProfesionales(
            @RequestParam(required = false) Boolean disponible) {
        return ResponseEntity.ok(profesionalService.listarDisponibles());
    }
}