package com.usta.mindbridge.service;

import com.usta.mindbridge.dto.request.FactorRiesgoRequest;
import com.usta.mindbridge.dto.response.FactorRiesgoResponse;
import com.usta.mindbridge.model.FactorRiesgo;
import com.usta.mindbridge.repository.FactorRiesgoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactorRiesgoService {

    private final FactorRiesgoRepository factorRiesgoRepository;

    public FactorRiesgoService(FactorRiesgoRepository factorRiesgoRepository) {
        this.factorRiesgoRepository = factorRiesgoRepository;
    }

    @Transactional(readOnly = true)
    public List<FactorRiesgoResponse> listarActivos() {
        return factorRiesgoRepository.findByActivoTrue()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FactorRiesgoResponse obtenerPorId(Long id) {
        return toResponse(factorRiesgoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factor de riesgo no encontrado:  + id")));
    }

    @Transactional
    public FactorRiesgoResponse crear(FactorRiesgoRequest request) {
        FactorRiesgo factor = new FactorRiesgo();
        mapear(request, factor);
        factor.setActivo(true);
        return toResponse(factorRiesgoRepository.save(factor));
    }

    @Transactional
    public FactorRiesgoResponse actualizar(Long id, FactorRiesgoRequest request) {
        FactorRiesgo factor = factorRiesgoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factor de riesgo no encontrado:  + id"));
        mapear(request, factor);
        return toResponse(factorRiesgoRepository.save(factor));
    }

    @Transactional
    public FactorRiesgoResponse toggleActivo(Long id) {
        FactorRiesgo factor = factorRiesgoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factor de riesgo no encontrado:  + id"));
        factor.setActivo(!factor.getActivo());
        return toResponse(factorRiesgoRepository.save(factor));
    }

    private void mapear(FactorRiesgoRequest request, FactorRiesgo factor) {
        factor.setNombre(request.getNombre());
        factor.setDescripcion(request.getDescripcion());
        factor.setPeso(request.getPeso());
        factor.setCategoria(request.getCategoria());
    }

    private FactorRiesgoResponse toResponse(FactorRiesgo f) {
        FactorRiesgoResponse r = new FactorRiesgoResponse();
        r.setId(f.getId());
        r.setNombre(f.getNombre());
        r.setDescripcion(f.getDescripcion());
        r.setPeso(f.getPeso());
        r.setCategoria(f.getCategoria());
        r.setActivo(f.getActivo());
        return r;
    }
}
