package com.usta.mindbridge.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "intervenciones")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Intervencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private LocalDateTime fecha;

    @Column(columnDefinition = "TEXT")
    private String resultado;

    private Integer duracionMin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alerta_id")
    private Alerta alerta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesional_id")
    private Profesional profesional;
}