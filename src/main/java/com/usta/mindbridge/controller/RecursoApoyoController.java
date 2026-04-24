package com.usta.mindbridge.controller;

import com.usta.mindbridge.dto.request.RecursoRequest;
import com.usta.mindbridge.dto.response.RecursoResponse;
import com.usta.mindbridge.service.RecursoApoyoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recursos")
public class RecursoApoyoController {

    private final RecursoApoyoService recursoApoyoService;

    public RecursoApoyoController(RecursoApoyoService recursoApoyoService) {
        this.recursoApoyoService = recursoApoyoService;
    }

    @GetMapping
    public ResponseEntity<Page<RecursoResponse>> listar(
            @RequestParam(required = false) String tipo, Pageable pageable) {
        return ResponseEntity.ok(recursoApoyoService.listar(tipo, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(recursoApoyoService.obtenerPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RecursoResponse> crear(@Valid @RequestBody RecursoRequest request) {
        return ResponseEntity.status(201).body(recursoApoyoService.crear(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RecursoResponse> actualizar(
            @PathVariable Long id, @Valid @RequestBody RecursoRequest request) {
        return ResponseEntity.ok(recursoApoyoService.actualizar(id, request));
    }

    @PatchMapping("/{id}/toggle")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RecursoResponse> toggle(@PathVariable Long id) {
        return ResponseEntity.ok(recursoApoyoService.toggleActivo(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recursoApoyoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}