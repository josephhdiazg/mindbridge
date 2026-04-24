package com.usta.mindbridge.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesionales")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String especialidad;

    @Column(nullable = false)
    @Builder.Default
    private Boolean disponible = true;

    private Integer maxCarga;

    @OneToMany(mappedBy = "profesional")
    @Builder.Default
    private List<Intervencion> intervenciones = new ArrayList<>();
}