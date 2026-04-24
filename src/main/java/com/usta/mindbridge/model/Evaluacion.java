package com.usta.mindbridge.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "evaluaciones")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private EstadoEvaluacion estado = EstadoEvaluacion.PENDIENTE;

    private Double puntajeTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private NivelRiesgo nivelRiesgo = NivelRiesgo.BAJO;

    @OneToMany(mappedBy = "evaluacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Respuesta> respuestas = new ArrayList<>();

    @OneToMany(mappedBy = "evaluacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Alerta> alertas = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        this.fecha = LocalDateTime.now();
    }
}