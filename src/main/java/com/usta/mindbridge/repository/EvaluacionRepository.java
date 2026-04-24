package com.usta.mindbridge.repository;

import com.usta.mindbridge.model.Evaluacion;
import com.usta.mindbridge.model.NivelRiesgo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {

    List<Evaluacion> findByEstudianteId(Long estudianteId);

    Page<Evaluacion> findByNivelRiesgo(NivelRiesgo nivelRiesgo, Pageable pageable);

    Page<Evaluacion> findAll(Pageable pageable);

    @Query("SELECT e FROM Evaluacion e WHERE e.fecha BETWEEN :desde AND :hasta")
    List<Evaluacion> findByFechaBetween(
            @Param("desde") LocalDateTime desde,
            @Param("hasta") LocalDateTime hasta);

    @Query("SELECT e FROM Evaluacion e WHERE e.estudiante.id = :estudianteId ORDER BY e.fecha DESC")
    Optional<Evaluacion> findUltimaEvaluacionByEstudiante(@Param("estudianteId") Long estudianteId,
                                                          Pageable pageable);
}