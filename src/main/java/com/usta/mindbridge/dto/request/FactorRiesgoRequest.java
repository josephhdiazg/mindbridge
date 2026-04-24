package com.usta.mindbridge.dto.request;

import com.usta.mindbridge.model.CategoriaFactor;
import jakarta.validation.constraints.*;

public class FactorRiesgoRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El peso es obligatorio")
    @DecimalMin(value = "0.0", message = "El peso mínimo es 0.0")
    @DecimalMax(value = "1.0", message = "El peso máximo es 1.0")
    private Double peso;

    @NotNull(message = "La categoría es obligatoria")
    private CategoriaFactor categoria;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public CategoriaFactor getCategoria() { return categoria; }
    public void setCategoria(CategoriaFactor categoria) { this.categoria = categoria; }
}