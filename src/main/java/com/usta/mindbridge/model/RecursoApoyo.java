package com.usta.mindbridge.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recursos_apoyo")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecursoApoyo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoRecurso tipo;

    @Column(unique = true)
    private String url;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ElementCollection
    @CollectionTable(name = "recurso_etiquetas", joinColumns = @JoinColumn(name = "recurso_id"))
    @Column(name = "etiqueta")
    @Builder.Default
    private List<String> etiquetas = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}