package com.usta.mindbridge.dto.response;

import com.usta.mindbridge.model.EstadoEvaluacion;
import com.usta.mindbridge.model.NivelRiesgo;
import java.time.LocalDateTime;

public class EvaluacionResponse {
    private Long id;
    private Long estudianteId;
    private String estudianteNombre;
    private LocalDateTime fecha;
    private EstadoEvaluacion estado;
    private Double puntajeTotal;
    private NivelRiesgo nivelRiesgo;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }
    public String getEstudianteNombre() { return estudianteNombre; }
    public void setEstudianteNombre(String estudianteNombre) { this.estudianteNombre = estudianteNombre; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public EstadoEvaluacion getEstado() { return estado; }
    public void setEstado(EstadoEvaluacion estado) { this.estado = estado; }
    public Double getPuntajeTotal() { return puntajeTotal; }
    public void setPuntajeTotal(Double puntajeTotal) { this.puntajeTotal = puntajeTotal; }
    public NivelRiesgo getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(NivelRiesgo nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }
}
