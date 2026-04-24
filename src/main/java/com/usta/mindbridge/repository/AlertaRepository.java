package com.usta.mindbridge.repository;

import com.usta.mindbridge.model.Alerta;
import com.usta.mindbridge.model.NivelRiesgo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    Page<Alerta> findByAtendidaFalseOrderByNivelDesc(Pageable pageable);

    Page<Alerta> findAll(Pageable pageable);

    @Query("SELECT a FROM Alerta a WHERE a.nivel = 'CRITICO' AND a.atendida = false")
    List<Alerta> findCriticasNoAtendidas();

    List<Alerta> findByEstudianteId(Long estudianteId);
}