package com.usta.mindbridge.service;

import com.mindbridge.dto.ai.*;
import com.mindbridge.model.NivelRiesgo;
import com.mindbridge.model.RecursoApoyo;
import com.mindbridge.repository.RecursoApoyoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AiService {

    private final RecursoApoyoRepository recursoApoyoRepository;

    // IDs de factores considerados críticos (acordar con Dev 1/2)
    private static final List<Long> FACTORES_CRITICOS_IDS = Arrays.asList(1L, 2L, 3L);

    public AiService(RecursoApoyoRepository recursoApoyoRepository) {
        this.recursoApoyoRepository = recursoApoyoRepository;
    }

    @Transactional(readOnly = true)
    public ClasificacionResponse clasificar(ClasificacionRequest req) {
        double puntaje = req.getPuntaje();
        NivelRiesgo nivel = NivelRiesgo.fromPuntaje(puntaje);

        // Ajuste por factores de alta criticidad
        long factoresCriticos = req.getFactoresActivados() != null
                ? req.getFactoresActivados().stream()
                .filter(this::esCritico).count()
                : 0;

        if (factoresCriticos >= 2 && nivel == NivelRiesgo.MEDIO) {
            nivel = NivelRiesgo.ALTO;
        }

        double confianza = calcularConfianza(puntaje, nivel);
        String justificacion = generarJustificacion(nivel, puntaje, factoresCriticos);

        return new ClasificacionResponse(nivel, confianza, justificacion);
    }

    @Transactional(readOnly = true)
    public TextoAnalisisResponse analizarTexto(TextoAnalisisRequest req) {
        String texto = req.getTexto().toLowerCase();

        List<String> palabrasRiesgo = Arrays.asList(
                "tristeza", "solo", "soledad", "angustia", "miedo",
                "ansiedad", "deprimido", "sin ganas", "cansado", "rendirme"
        );

        List<String> fragmentosDetectados = palabrasRiesgo.stream()
                .filter(texto::contains)
                .collect(Collectors.toList());

        boolean riesgoDetectado = !fragmentosDetectados.isEmpty();

        List<String> etiquetas = riesgoDetectado
                ? Arrays.asList("RIESGO_EMOCIONAL", "REQUIERE_REVISION")
                : Arrays.asList("SIN_RIESGO_DETECTADO");

        TextoAnalisisResponse response = new TextoAnalisisResponse();
        response.setEtiquetas(etiquetas);
        response.setRiesgoDetectado(riesgoDetectado);
        response.setFragmentos(fragmentosDetectados);
        return response;
    }

    @Transactional(readOnly = true)
    public List<RecursoDTO> recomendar(Long estudianteId) {
        // Por ahora retorna recursos activos con etiquetas de bienestar general
        List<String> etiquetasBusqueda = Arrays.asList("bienestar", "apoyo", "salud-mental");
        List<RecursoApoyo> recursos = recursoApoyoRepository.findByEtiquetasIn(etiquetasBusqueda);

        return recursos.stream()
                .limit(5)
                .map(this::toRecursoDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PatronResponse detectarPatron(Long estudianteId) {
        // Implementación básica — expandir con historial real cuando Dev2 esté disponible
        PatronResponse response = new PatronResponse();
        response.setPatron("SIN_PATRON_DEFINIDO");
        response.setTendencia("ESTABLE");
        response.setEvaluacionesAnalizadas(0);
        return response;
    }

    // ─── Métodos privados ───────────────────────────────────────────────────

    private boolean esCritico(Long factorId) {
        return FACTORES_CRITICOS_IDS.contains(factorId);
    }

    private double calcularConfianza(double puntaje, NivelRiesgo nivel) {
        return switch (nivel) {
            case CRITICO -> puntaje >= 90 ? 0.95 : 0.80;
            case ALTO    -> puntaje >= 65 ? 0.85 : 0.70;
            case MEDIO   -> puntaje >= 40 ? 0.75 : 0.65;
            case BAJO    -> 0.90;
        };
    }

    private String generarJustificacion(NivelRiesgo nivel, double puntaje, long factoresCriticos) {
        return String.format(
                "Nivel %s determinado con puntaje %.1f. Factores críticos activados: %d.",
                nivel.name(), puntaje, factoresCriticos
        );
    }

    private RecursoDTO toRecursoDTO(RecursoApoyo recurso) {
        RecursoDTO dto = new RecursoDTO();
        dto.setId(recurso.getId());
        dto.setTitulo(recurso.getTitulo());
        dto.setUrl(recurso.getUrl());
        dto.setDescripcion(recurso.getDescripcion());
        dto.setEtiquetas(recurso.getEtiquetas());
        return dto;
    }
}