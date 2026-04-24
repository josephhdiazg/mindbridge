package com.usta.mindbridge.model;

public enum NivelRiesgo {
    BAJO, MEDIO, ALTO, CRITICO;

    public static NivelRiesgo fromPuntaje(double puntaje) {
        if (puntaje < 30) return BAJO;
        if (puntaje < 55) return MEDIO;
        if (puntaje < 75) return ALTO;
        return CRITICO;
    }
}
