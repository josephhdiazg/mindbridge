package com.usta.mindbridge.dto.recurso.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public class RecursoRequest {

    @NotBlank
    private String titulo;

    @NotNull
    private String tipo;

    @URL
    private String url;

    @NotBlank
    private String descripcion;

    @NotEmpty
    private List<String> etiquetas;

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<String> getEtiquetas() { return etiquetas; }
    public void setEtiquetas(List<String> etiquetas) { this.etiquetas = etiquetas; }
}