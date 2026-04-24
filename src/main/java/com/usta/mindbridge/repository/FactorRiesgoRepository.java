package com.usta.mindbridge.repository;

import com.usta.mindbridge.model.FactorRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FactorRiesgoRepository extends JpaRepository<FactorRiesgo, Long> {
    List<FactorRiesgo> findByActivoTrue();
}