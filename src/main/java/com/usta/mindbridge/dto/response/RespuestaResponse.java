package com.usta.mindbridge.dto.response;

import java.time.LocalDateTime;

public class RespuestaResponse {
    private Long id;
    private Long factorRiesgoId;
    private String factorRiesgoNombre;
    private Integer valorRespuesta;
    private LocalDateTime timestamp;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFactorRiesgoId() { return factorRiesgoId; }
    public void setFactorRiesgoId(Long factorRiesgoId) { this.factorRiesgoId = factorRiesgoId; }
    public String getFactorRiesgoNombre() { return factorRiesgoNombre; }
    public void setFactorRiesgoNombre(String factorRiesgoNombre) { this.factorRiesgoNombre = factorRiesgoNombre; }
    public Integer getValorRespuesta() { return valorRespuesta; }
    public void setValorRespuesta(Integer valorRespuesta) { this.valorRespuesta = valorRespuesta; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
