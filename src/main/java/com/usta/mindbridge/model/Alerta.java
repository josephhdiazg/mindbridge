package com.usta.mindbridge.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alertas")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelRiesgo nivel;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @Column(nullable = false)
    @Builder.Default
    private Boolean atendida = false;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaAtencion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluacion_id")
    private Evaluacion evaluacion;

    @OneToMany(mappedBy = "alerta", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Intervencion> intervenciones = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}