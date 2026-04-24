package com.usta.mindbridge.dto.response;

import com.usta.mindbridge.model.CategoriaFactor;

public class FactorRiesgoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double peso;
    private CategoriaFactor categoria;
    private Boolean activo;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }
    public CategoriaFactor getCategoria() { return categoria; }
    public void setCategoria(CategoriaFactor categoria) { this.categoria = categoria; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
