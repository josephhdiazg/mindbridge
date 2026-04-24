package com.usta.mindbridge.service;

import com.mindbridge.dto.recurso.RecursoRequest;
import com.mindbridge.dto.recurso.RecursoResponse;
import com.mindbridge.model.RecursoApoyo;
import com.mindbridge.repository.RecursoApoyoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecursoApoyoService {

    private final RecursoApoyoRepository recursoApoyoRepository;

    public RecursoApoyoService(RecursoApoyoRepository recursoApoyoRepository) {
        this.recursoApoyoRepository = recursoApoyoRepository;
    }

    @Transactional(readOnly = true)
    public Page<RecursoResponse> listar(String tipo, Pageable pageable) {
        Page<RecursoApoyo> recursos = (tipo != null)
                ? recursoApoyoRepository.findByTipoAndActivo(tipo, true, pageable)
                : recursoApoyoRepository.findByActivo(true, pageable);
        return recursos.map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public RecursoResponse obtenerPorId(Long id) {
        RecursoApoyo recurso = recursoApoyoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recurso no encontrado: " + id));
        return toResponse(recurso);
    }

    @Transactional
    public RecursoResponse crear(RecursoRequest request) {
        RecursoApoyo recurso = new RecursoApoyo();
        mapRequestToEntity(request, recurso);
        recurso.setActivo(true);
        return toResponse(recursoApoyoRepository.save(recurso));
    }

    @Transactional
    public RecursoResponse actualizar(Long id, RecursoRequest request) {
        RecursoApoyo recurso = recursoApoyoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recurso no encontrado: " + id));
        mapRequestToEntity(request, recurso);
        return toResponse(recursoApoyoRepository.save(recurso));
    }

    @Transactional
    public RecursoResponse toggleActivo(Long id) {
        RecursoApoyo recurso = recursoApoyoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recurso no encontrado: " + id));
        recurso.setActivo(!recurso.getActivo());
        return toResponse(recursoApoyoRepository.save(recurso));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!recursoApoyoRepository.existsById(id)) {
            throw new EntityNotFoundException("Recurso no encontrado: " + id);
        }
        recursoApoyoRepository.deleteById(id);
    }

    // ─── Helpers ───────────────────────────────────────────────────────────

    private void mapRequestToEntity(RecursoRequest request, RecursoApoyo recurso) {
        recurso.setTitulo(request.getTitulo());
        recurso.setTipo(request.getTipo());
        recurso.setUrl(request.getUrl());
        recurso.setDescripcion(request.getDescripcion());
        recurso.setEtiquetas(request.getEtiquetas());
    }

    private RecursoResponse toResponse(RecursoApoyo recurso) {
        RecursoResponse response = new RecursoResponse();
        response.setId(recurso.getId());
        response.setTitulo(recurso.getTitulo());
        response.setTipo(recurso.getTipo());
        response.setUrl(recurso.getUrl());
        response.setDescripcion(recurso.getDescripcion());
        response.setEtiquetas(recurso.getEtiquetas());
        response.setActivo(recurso.getActivo());
        return response;
    }
}