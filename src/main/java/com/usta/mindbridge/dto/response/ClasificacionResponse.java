package com.usta.mindbridge.dto.response;

import com.usta.mindbridge.model.NivelRiesgo;

public class ClasificacionResponse {

    private NivelRiesgo nivelRiesgo;
    private Double confianza;
    private String justificacion;

    public ClasificacionResponse() {}

    public ClasificacionResponse(NivelRiesgo nivelRiesgo, Double confianza, String justificacion) {
        this.nivelRiesgo = nivelRiesgo;
        this.confianza = confianza;
        this.justificacion = justificacion;
    }

    public NivelRiesgo getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(NivelRiesgo nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }

    public Double getConfianza() { return confianza; }
    public void setConfianza(Double confianza) { this.confianza = confianza; }

    public String getJustificacion() { return justificacion; }
    public void setJustificacion(String justificacion) { this.justificacion = justificacion; }
}