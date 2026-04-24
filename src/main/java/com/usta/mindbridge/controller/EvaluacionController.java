package com.usta.mindbridge.controller;

import com.usta.mindbridge.dto.request.EvaluacionInicioRequest;
import com.usta.mindbridge.dto.request.RespuestaRequest;
import com.usta.mindbridge.dto.response.AlertaResumenResponse;
import com.usta.mindbridge.dto.response.EvaluacionResponse;
import com.usta.mindbridge.dto.response.ResultadoResponse;
import com.usta.mindbridge.model.NivelRiesgo;
import com.usta.mindbridge.service.AlertaService;
import com.usta.mindbridge.service.EvaluacionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;
    private final AlertaService alertaService;

    public EvaluacionController(EvaluacionService evaluacionService, AlertaService alertaService) {
        this.evaluacionService = evaluacionService;
        this.alertaService = alertaService;
    }

    @PostMapping
    public ResponseEntity<EvaluacionResponse> iniciar(@Valid @RequestBody EvaluacionInicioRequest request) {
        return ResponseEntity.status(201).body(evaluacionService.iniciar(request));
    }

    @PostMapping("/{id}/respuestas")
    public ResponseEntity<EvaluacionResponse> responder(
            @PathVariable Long id,
            @Valid @RequestBody List<RespuestaRequest> respuestas) {
        return ResponseEntity.ok(evaluacionService.agregarRespuestasYFinalizar(id, respuestas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(evaluacionService.obtenerPorId(id));
    }

    @GetMapping("/{id}/resultado")
    public ResponseEntity<ResultadoResponse> resultado(@PathVariable Long id) {
        return ResponseEntity.ok(evaluacionService.obtenerResultado(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_PSICOLOGO')")
    public ResponseEntity<Page<EvaluacionResponse>> listar(
            @RequestParam(required = false) NivelRiesgo nivel,
            Pageable pageable) {
        return ResponseEntity.ok(evaluacionService.listar(nivel, pageable));
    }

    @GetMapping("/estudiante/{estudianteId}/alertas")
    @PreAuthorize("hasAnyRole('ROLE_PSICOLOGO', 'ROLE_ESTUDIANTE')")
    public ResponseEntity<List<AlertaResumenResponse>> alertasPorEstudiante(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(alertaService.listarPorEstudiante(estudianteId));
    }
}
