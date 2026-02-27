package com.eduplatlearn.service.impl;


import com.eduplatlearn.dto.lecon.LeconCreateRequestDTO;
import com.eduplatlearn.dto.lecon.LeconResponseDTO;
import com.eduplatlearn.dto.lecon.LeconUpdateRequestDTO;
import com.eduplatlearn.dto.module.ModuleShortDTO;
import com.eduplatlearn.entity.Lecon;
import com.eduplatlearn.entity.Module;
import com.eduplatlearn.repository.LeconRepository;
import com.eduplatlearn.repository.ModuleRepository;
import com.eduplatlearn.service.LeconService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class LeconServiceImpl implements LeconService {

    private final LeconRepository leconRepository;
    private final ModuleRepository moduleRepository;

    public LeconServiceImpl(LeconRepository leconRepository, ModuleRepository moduleRepository) {
        this.leconRepository = leconRepository;
        this.moduleRepository = moduleRepository;
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
            throw new IllegalArgumentException("TOUT EST OBLIGATOIRE");
        }
        if (request.titre() == null || request.titre().isBlank()) {
            throw new IllegalArgumentException("titre est obligatoire");
        }
        if (request.resume() == null || request.resume().isBlank()) {
            throw new IllegalArgumentException("resume est obligatoire");
        }
        if (request.ordre() == null || request.ordre().describeConstable().isEmpty()) {
            throw new IllegalArgumentException("ordre est obligatoire");
        }
        if (request.dureeMinutes() == null || request.dureeMinutes().describeConstable().isEmpty()) {
            throw new IllegalArgumentException("temps est obligatoire");
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
            throw new IllegalArgumentException("ATTENTION TOUS LES CHAMPS SONT OBLIGATOIRE");
        }

        Lecon lecon = leconRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("lecon introuvable: " + id));

        applyUpdate(lecon, request);

        // save() n'est pas strictement obligatoire si l'entité est managée,
        // mais on le garde pour être explicite pédagogiquement
        Lecon saved = leconRepository.save(lecon);
        return toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        if (!leconRepository.existsById(id)) {
            throw new IllegalArgumentException("lecon introuvable: " + id);
        }
        leconRepository.deleteById(id);
    }

    // ----------------------
    // MAPPING (Service Layer)
    // ----------------------

    private LeconResponseDTO toResponse(Lecon lecon) {
        // On récupère l'objet Module unique de la lecon
        Module moduledelaLecon = lecon.getModule();

        // On crée un seul DTO au lieu d'une liste
        ModuleShortDTO moduleShortDTO = null;

        if (moduledelaLecon != null) {
            moduleShortDTO = new ModuleShortDTO(
                    moduledelaLecon.getId(),
                    moduledelaLecon.getTitre()
            );
        }
        return new LeconResponseDTO(
                lecon.getId(),
                lecon.getTitre(),
                lecon.getResume(),
                lecon.getOrdre(),
                lecon.getDureeMinutes(),
                moduleShortDTO,
                lecon.getCreatedAt(),
                lecon.getUpdatedAt()
        );
    }

    private void applyCreate(Lecon lecon, LeconCreateRequestDTO req) {
        lecon.setTitre(req.titre());
        lecon.setResume(req.resume());
        lecon.setOrdre(req.ordre());
        lecon.setDureeMinutes(req.dureeMinutes());

        // On récupère l'ID unique du module
        if (req.module() != null) {
            // On cherche l'unique entité Module
            Module module = moduleRepository.findById(req.module())
                    .orElseThrow(() -> new RuntimeException("Module non trouvé avec l'ID : " + req.module()));

            // On affecte l'objet seul a la lecon
            lecon.setModule(module);
        }
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
