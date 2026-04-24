package com.usta.mindbridge.service;

import com.usta.mindbridge.dto.response.AlertaResponse;
import com.usta.mindbridge.dto.response.AlertaResumenResponse;
import com.usta.mindbridge.model.Alerta;
import com.usta.mindbridge.model.Evaluacion;
import com.usta.mindbridge.model.NivelRiesgo;
import com.usta.mindbridge.repository.AlertaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;

    public AlertaService(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @Transactional
    public void generarAlerta(Evaluacion evaluacion) {
        Alerta alerta = new Alerta();
        alerta.setNivel(evaluacion.getNivelRiesgo());
        alerta.setMensaje(generarMensaje(evaluacion.getNivelRiesgo(), evaluacion.getPuntajeTotal()));
        alerta.setAtendida(false);
        alerta.setEstudiante(evaluacion.getEstudiante());
        alerta.setEvaluacion(evaluacion);
        alertaRepository.save(alerta);
    }

    @Transactional(readOnly = true)
    public Page<AlertaResumenResponse> listarPriorizadas(Pageable pageable) {
        return alertaRepository.findByAtendidaFalseOrderByNivelDesc(pageable)
                .map(this::toResumen);
    }

    @Transactional(readOnly = true)
    public AlertaResponse obtenerPorId(Long id) {
        return toResponse(alertaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta no encontrada: " + id)));
    }

    @Transactional
    public AlertaResponse marcarAtendida(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta no encontrada: " + id));
        alerta.setAtendida(true);
        alerta.setFechaAtencion(LocalDateTime.now());
        return toResponse(alertaRepository.save(alerta));
    }

    @Transactional(readOnly = true)
    public List<AlertaResumenResponse> listarCriticas() {
        return alertaRepository.findCriticasNoAtendidas()
                .stream().map(this::toResumen).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AlertaResumenResponse> listarPorEstudiante(Long estudianteId) {
        return alertaRepository.findByEstudianteId(estudianteId)
                .stream().map(this::toResumen).collect(Collectors.toList());
    }

    private String generarMensaje(NivelRiesgo nivel, Double puntaje) {
        return String.format("Alerta de nivel %s generada automáticamente. Puntaje: %.1f. Requiere revisión profesional.",
                nivel.name(), puntaje);
    }

    private AlertaResponse toResponse(Alerta a) {
        AlertaResponse r = new AlertaResponse();
        r.setId(a.getId());
        r.setNivel(a.getNivel());
        r.setMensaje(a.getMensaje());
        r.setAtendida(a.getAtendida());
        r.setFechaCreacion(a.getFechaCreacion());
        r.setFechaAtencion(a.getFechaAtencion());
        r.setEstudianteId(a.getEstudiante().getId());
        r.setEstudianteNombre(a.getEstudiante().getNombre());
        if (a.getEvaluacion() != null) r.setEvaluacionId(a.getEvaluacion().getId());
        return r;
    }

    private AlertaResumenResponse toResumen(Alerta a) {
        AlertaResumenResponse r = new AlertaResumenResponse();
        r.setId(a.getId());
        r.setNivel(a.getNivel());
        r.setEstudianteNombre(a.getEstudiante().getNombre());
        r.setFechaCreacion(a.getFechaCreacion());
        r.setAtendida(a.getAtendida());
        return r;
    }
}
