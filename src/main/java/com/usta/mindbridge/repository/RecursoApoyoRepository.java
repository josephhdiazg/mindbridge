package com.usta.mindbridge.repository;

import com.usta.mindbridge.repository.RecursoApoyoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecursoApoyoRepository extends JpaRepository<RecursoApoyo, Long> {

    Page<RecursoApoyo> findByTipoAndActivo(String tipo, Boolean activo, Pageable pageable);

    Page<RecursoApoyo> findByActivo(Boolean activo, Pageable pageable);

    @Query("SELECT r FROM RecursoApoyo r JOIN r.etiquetas e WHERE e IN :etiquetas AND r.activo = true")
    List<RecursoApoyo> findByEtiquetasIn(@Param("etiquetas") List<String> etiquetas);
}