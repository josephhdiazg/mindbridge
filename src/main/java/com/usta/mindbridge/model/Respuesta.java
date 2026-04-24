package com.usta.mindbridge.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluacion_id", nullable = false)
    private Evaluacion evaluacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "factor_riesgo_id", nullable = false)
    private FactorRiesgo factorRiesgo;

    @Column(nullable = false)
    private Integer valorRespuesta;

    private LocalDateTime timestamp;

    @PrePersist
    private void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}