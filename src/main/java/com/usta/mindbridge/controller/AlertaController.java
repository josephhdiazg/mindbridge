package com.usta.mindbridge.controller;

import com.usta.mindbridge.dto.response.AlertaResponse;
import com.usta.mindbridge.dto.response.AlertaResumenResponse;
import com.usta.mindbridge.service.AlertaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_PSICOLOGO', 'ROLE_ESTUDIANTE')")
    public ResponseEntity<Page<AlertaResumenResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(alertaService.listarPriorizadas(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_PSICOLOGO', 'ROLE_ESTUDIANTE')")
    public ResponseEntity<AlertaResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(alertaService.obtenerPorId(id));
    }

    @PatchMapping("/{id}/atender")
    @PreAuthorize("hasAnyRole('ROLE_PSICOLOGO', 'ROLE_ESTUDIANTE')")
    public ResponseEntity<AlertaResponse> atender(@PathVariable Long id) {
        return ResponseEntity.ok(alertaService.marcarAtendida(id));
    }

    @GetMapping("/criticas")
    @PreAuthorize("hasAnyRole('ROLE_PSICOLOGO', 'ROLE_ESTUDIANTE')")
    public ResponseEntity<List<AlertaResumenResponse>> criticas() {
        return ResponseEntity.ok(alertaService.listarCriticas());
    }
}
