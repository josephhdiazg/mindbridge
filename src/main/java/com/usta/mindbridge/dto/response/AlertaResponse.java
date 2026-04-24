package com.usta.mindbridge.dto.response;

import com.usta.mindbridge.model.NivelRiesgo;
import java.time.LocalDateTime;

public class AlertaResponse {
    private Long id;
    private NivelRiesgo nivel;
    private String mensaje;
    private Boolean atendida;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaAtencion;
    private Long estudianteId;
    private String estudianteNombre;
    private Long evaluacionId;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public NivelRiesgo getNivel() { return nivel; }
    public void setNivel(NivelRiesgo nivel) { this.nivel = nivel; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public Boolean getAtendida() { return atendida; }
    public void setAtendida(Boolean atendida) { this.atendida = atendida; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public LocalDateTime getFechaAtencion() { return fechaAtencion; }
    public void setFechaAtencion(LocalDateTime fechaAtencion) { this.fechaAtencion = fechaAtencion; }
    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }
    public String getEstudianteNombre() { return estudianteNombre; }
    public void setEstudianteNombre(String estudianteNombre) { this.estudianteNombre = estudianteNombre; }
    public Long getEvaluacionId() { return evaluacionId; }
    public void setEvaluacionId(Long evaluacionId) { this.evaluacionId = evaluacionId; }
}
