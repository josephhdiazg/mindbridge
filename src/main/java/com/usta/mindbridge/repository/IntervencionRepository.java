package com.usta.mindbridge.repository;

import com.mindbridge.model.Intervencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervencionRepository extends JpaRepository<Intervencion, Long> {

    List<Intervencion> findByAlertaId(Long alertaId);

    List<Intervencion> findByProfesionalId(Long profesionalId);
}