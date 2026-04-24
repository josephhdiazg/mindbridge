package com.usta.mindbridge.dto.response;

import com.usta.mindbridge.model.NivelRiesgo;
import java.time.LocalDateTime;

public class AlertaResumenResponse {
    private Long id;
    private NivelRiesgo nivel;
    private String estudianteNombre;
    private LocalDateTime fechaCreacion;
    private Boolean atendida;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public NivelRiesgo getNivel() { return nivel; }
    public void setNivel(NivelRiesgo nivel) { this.nivel = nivel; }
    public String getEstudianteNombre() { return estudianteNombre; }
    public void setEstudianteNombre(String estudianteNombre) { this.estudianteNombre = estudianteNombre; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public Boolean getAtendida() { return atendida; }
    public void setAtendida(Boolean atendida) { this.atendida = atendida; }
}
