package com.usta.mindbridge.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "factores_riesgo")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactorRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, precision = 3)
    private Double peso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaFactor categoria;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;

    @OneToMany(mappedBy = "factorRiesgo")
    @Builder.Default
    private List<Respuesta> respuestas = new ArrayList<>();
}