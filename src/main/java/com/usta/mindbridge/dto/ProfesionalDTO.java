package com.usta.mindbridge.dto;

public class ProfesionalDTO {

    private Long id;
    private String especialidad;
    private Boolean disponible;
    private Integer maxCarga;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }

    public Integer getMaxCarga() { return maxCarga; }
    public void setMaxCarga(Integer maxCarga) { this.maxCarga = maxCarga; }
}
