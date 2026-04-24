package com.usta.mindbridge.dto.response;

import com.usta.mindbridge.model.NivelRiesgo;
import java.time.LocalDateTime;
import java.util.List;

public class ResultadoResponse {
    private Double puntajeTotal;
    private NivelRiesgo nivelRiesgo;
    private LocalDateTime fecha;
    private List<String> recomendaciones;

    // getters y setters
    public Double getPuntajeTotal() { return puntajeTotal; }
    public void setPuntajeTotal(Double puntajeTotal) { this.puntajeTotal = puntajeTotal; }
    public NivelRiesgo getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(NivelRiesgo nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public List<String> getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(List<String> recomendaciones) { this.recomendaciones = recomendaciones; }
}
