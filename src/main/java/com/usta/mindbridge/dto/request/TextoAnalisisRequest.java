package com.usta.mindbridge.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TextoAnalisisRequest {

    @NotBlank
    @Size(max = 1000)
    private String texto;

    @NotNull
    private Long estudianteId;

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }
}