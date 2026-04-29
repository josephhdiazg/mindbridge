package com.usta.mindbridge.service;

import com.usta.mindbridge.dto.request.EvaluacionInicioRequest;
import com.usta.mindbridge.dto.request.RespuestaRequest;
import com.usta.mindbridge.dto.response.EvaluacionResponse;
import com.usta.mindbridge.dto.response.ResultadoResponse;
import com.usta.mindbridge.exception.custom.ResourceNotFoundException;
import com.usta.mindbridge.model.*;
import com.usta.mindbridge.repository.EstudianteRepository;
import com.usta.mindbridge.repository.EvaluacionRepository;
import com.usta.mindbridge.repository.FactorRiesgoRepository;
import com.usta.mindbridge.repository.RespuestaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final EstudianteRepository estudianteRepository;
    private final FactorRiesgoRepository factorRiesgoRepository;
    private final RespuestaRepository respuestaRepository;
    private final AlertaService alertaService;

    public EvaluacionService(EvaluacionRepository evaluacionRepository,
                              EstudianteRepository estudianteRepository,
                              FactorRiesgoRepository factorRiesgoRepository,
                              RespuestaRepository respuestaRepository,
                              AlertaService alertaService) {
        this.evaluacionRepository = evaluacionRepository;
        this.estudianteRepository = estudianteRepository;
        this.factorRiesgoRepository = factorRiesgoRepository;
        this.respuestaRepository = respuestaRepository;
        this.alertaService = alertaService;
    }

    @Transactional
    public EvaluacionResponse iniciar(EvaluacionInicioRequest request) {
        Estudiante estudiante = estudianteRepository.findById(request.getEstudianteId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado: " + request.getEstudianteId()));
        Evaluacion evaluacion = Evaluacion.builder()
                .estudiante(estudiante)
                .estado(EstadoEvaluacion.PENDIENTE)
                .nivelRiesgo(NivelRiesgo.BAJO)
                .build();
        return toResponse(evaluacionRepository.save(evaluacion));
    }

    @Transactional
    public EvaluacionResponse agregarRespuestasYFinalizar(Long evaluacionId, List<RespuestaRequest> dtos) {
        Evaluacion evaluacion = evaluacionRepository.findById(evaluacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluacion no encontrada: " + evaluacionId));

        List<Respuesta> respuestas = dtos.stream().map(dto -> {
            FactorRiesgo factor = factorRiesgoRepository.findById(dto.getFactorRiesgoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Factor no encontrado: " + dto.getFactorRiesgoId()));
            return Respuesta.builder()
                    .evaluacion(evaluacion)
                    .factorRiesgo(factor)
                    .valorRespuesta(dto.getValorRespuesta())
                    .build();
        }).collect(Collectors.toList());

        respuestaRepository.saveAll(respuestas);
        evaluacion.getRespuestas().addAll(respuestas);

        double puntaje = calcularPuntaje(evaluacion.getRespuestas());
        NivelRiesgo nivel = NivelRiesgo.fromPuntaje(puntaje);

        evaluacion.setPuntajeTotal(puntaje);
        evaluacion.setNivelRiesgo(nivel);
        evaluacion.setEstado(EstadoEvaluacion.COMPLETADA);

        if (nivel == NivelRiesgo.ALTO || nivel == NivelRiesgo.CRITICO) {
            alertaService.generarAlerta(evaluacion);
        }

        return toResponse(evaluacionRepository.save(evaluacion));
    }

    @Transactional(readOnly = true)
    public EvaluacionResponse obtenerPorId(Long id) {
        return toResponse(evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluacion no encontrada: " + id)));
    }

    @Transactional(readOnly = true)
    public ResultadoResponse obtenerResultado(Long id) {
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluacion no encontrada: " + id));
        ResultadoResponse r = new ResultadoResponse();
        r.setPuntajeTotal(evaluacion.getPuntajeTotal());
        r.setNivelRiesgo(evaluacion.getNivelRiesgo());
        r.setFecha(evaluacion.getFecha());
        r.setRecomendaciones(generarRecomendaciones(evaluacion.getNivelRiesgo()));
        return r;
    }

    @Transactional(readOnly = true)
    public Page<EvaluacionResponse> listar(NivelRiesgo nivel, Pageable pageable) {
        Page<Evaluacion> page = (nivel != null)
                ? evaluacionRepository.findByNivelRiesgo(nivel, pageable)
                : evaluacionRepository.findAll(pageable);
        return page.map(this::toResponse);
    }

    private double calcularPuntaje(List<Respuesta> respuestas) {
        return respuestas.stream()
                .mapToDouble(r -> r.getValorRespuesta() * r.getFactorRiesgo().getPeso() * 20)
                .sum();
    }

    private List<String> generarRecomendaciones(NivelRiesgo nivel) {
        return switch (nivel) {
            case BAJO -> Arrays.asList("Mantén hábitos saludables", "Practica actividad física regularmente");
            case MEDIO -> Arrays.asList("Considera hablar con un consejero", "Revisa técnicas de manejo del estrés");
            case ALTO -> Arrays.asList("Se recomienda consulta con psicólogo", "Contacta el servicio de bienestar universitario");
            case CRITICO -> Arrays.asList("Atención profesional urgente requerida", "Contacta inmediatamente al equipo de salud mental");
        };
    }

    private EvaluacionResponse toResponse(Evaluacion e) {
        EvaluacionResponse r = new EvaluacionResponse();
        r.setId(e.getId());
        r.setEstudianteId(e.getEstudiante().getId());
        r.setEstudianteNombre(e.getEstudiante().getNombre());
        r.setFecha(e.getFecha());
        r.setEstado(e.getEstado());
        r.setPuntajeTotal(e.getPuntajeTotal());
        r.setNivelRiesgo(e.getNivelRiesgo());
        return r;
    }
}
