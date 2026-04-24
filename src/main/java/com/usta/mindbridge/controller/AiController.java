package com.usta.mindbridge.controller;

import com.mindbridge.dto.ai.*;
import com.mindbridge.service.AiService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/clasificar")
    @PreAuthorize("hasAnyRole('PSICOLOGO', 'ADMIN')")
    public ResponseEntity<ClasificacionResponse> clasificar(@Valid @RequestBody ClasificacionRequest request) {
        return ResponseEntity.ok(aiService.clasificar(request));
    }

    @PostMapping("/analizar-texto")
    @PreAuthorize("hasRole('PSICOLOGO')")
    public ResponseEntity<TextoAnalisisResponse> analizarTexto(@Valid @RequestBody TextoAnalisisRequest request) {
        return ResponseEntity.ok(aiService.analizarTexto(request));
    }

    @GetMapping("/recomendar/{estudianteId}")
    @PreAuthorize("hasAnyRole('PSICOLOGO', 'ADMIN') or #estudianteId == authentication.principal.id")
    public ResponseEntity<List<RecursoDTO>> recomendar(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(aiService.recomendar(estudianteId));
    }

    @PostMapping("/patron/{estudianteId}")
    @PreAuthorize("hasRole('PSICOLOGO')")
    public ResponseEntity<PatronResponse> detectarPatron(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(aiService.detectarPatron(estudianteId));
    }
}