package com.usta.mindbridge.dto.response;

import java.util.List;

public class TextoAnalisisResponse {

    private List<String> etiquetas;
    private Boolean riesgoDetectado;
    private List<String> fragmentos;

    // Getters y Setters
    public List<String> getEtiquetas() { return etiquetas; }
    public void setEtiquetas(List<String> etiquetas) { this.etiquetas = etiquetas; }

    public Boolean getRiesgoDetectado() { return riesgoDetectado; }
    public void setRiesgoDetectado(Boolean riesgoDetectado) { this.riesgoDetectado = riesgoDetectado; }

    public List<String> getFragmentos() { return fragmentos; }
    public void setFragmentos(List<String> fragmentos) { this.fragmentos = fragmentos; }
}