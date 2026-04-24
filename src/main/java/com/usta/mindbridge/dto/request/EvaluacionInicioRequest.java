package com.usta.mindbridge.dto.request;

import jakarta.validation.constraints.NotNull;

public class EvaluacionInicioRequest {

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;

    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }
}