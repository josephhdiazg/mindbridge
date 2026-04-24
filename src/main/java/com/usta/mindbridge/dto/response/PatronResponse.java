package com.usta.mindbridge.dto.response;

public class PatronResponse {

    private String patron;
    private String tendencia;
    private Integer evaluacionesAnalizadas;

    public String getPatron() { return patron; }
    public void setPatron(String patron) { this.patron = patron; }

    public String getTendencia() { return tendencia; }
    public void setTendencia(String tendencia) { this.tendencia = tendencia; }

    public Integer getEvaluacionesAnalizadas() { return evaluacionesAnalizadas; }
    public void setEvaluacionesAnalizadas(Integer evaluacionesAnalizadas) { this.evaluacionesAnalizadas = evaluacionesAnalizadas; }
}