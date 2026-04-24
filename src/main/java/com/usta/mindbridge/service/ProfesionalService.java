/*package com.usta.mindbridge.service;

import com.mindbridge.dto.intervencion.ProfesionalDTO;
import com.mindbridge.model.Profesional;
import com.mindbridge.repository.ProfesionalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesionalService {

    private final ProfesionalRepository profesionalRepository;

    public ProfesionalService(ProfesionalRepository profesionalRepository) {
        this.profesionalRepository = profesionalRepository;
    }

    @Transactional(readOnly = true)
    public List<ProfesionalDTO> listarDisponibles() {
        return profesionalRepository.findByDisponibleTrue()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    private ProfesionalDTO toDTO(Profesional p) {
        ProfesionalDTO dto = new ProfesionalDTO();
        dto.setId(p.getId());
        dto.setEspecialidad(p.getEspecialidad());
        dto.setDisponible(p.getDisponible());
        dto.setMaxCarga(p.getMaxCarga());
        return dto;
    }
}