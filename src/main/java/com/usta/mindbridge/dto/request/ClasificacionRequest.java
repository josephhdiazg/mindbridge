package com.usta.mindbridge.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ClasificacionRequest {

    @NotNull
    private Long evaluacionId;

    @NotNull
    private Double puntaje;

    private List<Long> factoresActivados;

    public Long getEvaluacionId() { return evaluacionId; }
    public void setEvaluacionId(Long evaluacionId) { this.evaluacionId = evaluacionId; }

    public Double getPuntaje() { return puntaje; }
    public void setPuntaje(Double puntaje) { this.puntaje = puntaje; }

    public List<Long> getFactoresActivados() { return factoresActivados; }
    public void setFactoresActivados(List<Long> factoresActivados) { this.factoresActivados = factoresActivados; }
}