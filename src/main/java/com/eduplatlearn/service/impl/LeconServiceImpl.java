package com.eduplatlearn.service.impl;


import com.eduplatlearn.dto.lecon.LeconCreateRequestDTO;
import com.eduplatlearn.dto.lecon.LeconResponseDTO;
import com.eduplatlearn.dto.lecon.LeconUpdateRequestDTO;
import com.eduplatlearn.entity.Lecon;
import com.eduplatlearn.repository.LeconRepository;
import com.eduplatlearn.service.LeconService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class LeconServiceImpl implements LeconService {

    private final LeconRepository leconRepository;

    public LeconServiceImpl(LeconRepository leconRepository) {
        this.leconRepository = leconRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeconResponseDTO> getAll() {
        return leconRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LeconResponseDTO getById(Long id) {
        Lecon lecon = leconRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("enseignant introuvable: " + id));
        return toResponse(lecon);
    }

    @Override
    public LeconResponseDTO create(LeconCreateRequestDTO request) {
        // Règles simples de robustesse (sans faire encore la slide Validation)
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (request.titre() == null || request.titre().isBlank()) {
            throw new IllegalArgumentException("titre is required");
        }
        if (request.resume() == null || request.resume().isBlank()) {
            throw new IllegalArgumentException("resume is required");
        }
        if (request.ordre() == null || request.resume().isBlank()) {
            throw new IllegalArgumentException("order is required");
        }
        if (request.dureeMinutes() == null || request.resume().isBlank()) {
            throw new IllegalArgumentException("time is required");
        }

        Lecon lecon = new Lecon();
        applyCreate(lecon, request);

        // createdAt/updatedAt : idéalement gérés par JPA/Auditing (partie suivante si besoin)
        Lecon saved = leconRepository.save(lecon );
        return toResponse(saved);
    }

    @Override
    public LeconResponseDTO update(Long id, LeconUpdateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        Lecon lecon = leconRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("lecon not found: " + id));

        applyUpdate(lecon, request);

        // save() n'est pas strictement obligatoire si l'entité est managée,
        // mais on le garde pour être explicite pédagogiquement
        Lecon saved = leconRepository.save(lecon);
        return toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        if (!leconRepository.existsById(id)) {
            throw new IllegalArgumentException("lecon not found: " + id);
        }
        leconRepository.deleteById(id);
    }

    // ----------------------
    // MAPPING (Service Layer)
    // ----------------------

    private LeconResponseDTO toResponse(Lecon lecon) {
        return new LeconResponseDTO(
                lecon.getId(),
                lecon.getTitre(),
                lecon.getResume(),
                lecon.getOrdre(),
                lecon.getDureeMinutes(),
                lecon.getCreatedAt(),
                lecon.getUpdatedAt()
        );
    }

    private void applyCreate(Lecon lecon, LeconCreateRequestDTO req) {
        lecon.setTitre(req.titre());
        lecon.setResume(req.resume());
        lecon.setOrdre(req.ordre());
        lecon.setDureeMinutes(req.dureeMinutes());
    }

    private void applyUpdate(Lecon lecon, LeconUpdateRequestDTO req) {
        // Ici on fait un update "complet" (PUT) : on remplace les champs.
        // Si tu veux du PATCH plus tard, on gérera différemment.
        lecon.setTitre(req.titre());
        lecon.setResume(req.resume());
        lecon.setOrdre(req.ordre());
        lecon.setDureeMinutes(req.dureeMinutes());
    }
}
