package com.usta.mindbridge.service;

import com.mindbridge.dto.intervencion.IntervencionDTO;
import com.mindbridge.dto.intervencion.IntervencionRequest;
import com.mindbridge.dto.intervencion.IntervencionResponse;
import com.mindbridge.model.Intervencion;
import com.mindbridge.repository.IntervencionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntervencionService {

    private final IntervencionRepository intervencionRepository;

    public IntervencionService(IntervencionRepository intervencionRepository) {
        this.intervencionRepository = intervencionRepository;
    }

    @Transactional
    public IntervencionResponse registrar(IntervencionRequest request) {
        Intervencion intervencion = new Intervencion();
        intervencion.setTipo(request.getTipo());
        intervencion.setDescripcion(request.getDescripcion());
        intervencion.setDuracionMin(request.getDuracionMin());
        intervencion.setFecha(LocalDateTime.now());
        // Nota: alertaId y profesionalId serán asignados con las entidades
        // cuando Dev1/Dev2 tenga sus repos listos. Por ahora se deja la estructura.
        return toResponse(intervencionRepository.save(intervencion));
    }

    @Transactional(readOnly = true)
    public IntervencionResponse obtenerPorId(Long id) {
        Intervencion intervencion = intervencionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Intervención no encontrada: " + id));
        return toResponse(intervencion);
    }

    @Transactional
    public IntervencionResponse actualizar(Long id, IntervencionRequest request) {
        Intervencion intervencion = intervencionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Intervención no encontrada: " + id));
        intervencion.setTipo(request.getTipo());
        intervencion.setDescripcion(request.getDescripcion());
        intervencion.setDuracionMin(request.getDuracionMin());
        return toResponse(intervencionRepository.save(intervencion));
    }

    @Transactional(readOnly = true)
    public List<IntervencionDTO> listarPorAlerta(Long alertaId) {
        return intervencionRepository.findByAlertaId(alertaId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ─── Helpers ───────────────────────────────────────────────────────────

    private IntervencionResponse toResponse(Intervencion i) {
        IntervencionResponse r = new IntervencionResponse();
        r.setId(i.getId());
        r.setTipo(i.getTipo());
        r.setDescripcion(i.getDescripcion());
        r.setFecha(i.getFecha());
        r.setResultado(i.getResultado());
        r.setDuracionMin(i.getDuracionMin());
        return r;
    }

    private IntervencionDTO toDTO(Intervencion i) {
        IntervencionDTO dto = new IntervencionDTO();
        dto.setId(i.getId());
        dto.setTipo(i.getTipo());
        dto.setDescripcion(i.getDescripcion());
        dto.setFecha(i.getFecha());
        dto.setResultado(i.getResultado());
        return dto;
    }
}