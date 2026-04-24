package com.usta.mindbridge.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RespuestaRequest {

    @NotNull(message = "El ID del factor de riesgo es obligatorio")
    private Long factorRiesgoId;

    @NotNull(message = "El valor de respuesta es obligatorio")
    @Min(value = 1, message = "El valor mínimo es 1")
    @Max(value = 5, message = "El valor máximo es 5")
    private Integer valorRespuesta;

    public Long getFactorRiesgoId() { return factorRiesgoId; }
    public void setFactorRiesgoId(Long factorRiesgoId) { this.factorRiesgoId = factorRiesgoId; }

    public Integer getValorRespuesta() { return valorRespuesta; }
    public void setValorRespuesta(Integer valorRespuesta) { this.valorRespuesta = valorRespuesta; }
}