package com.eduplatlearn.service.impl;


import com.eduplatlearn.dto.cours.CoursShortDTO;
import com.eduplatlearn.dto.module.ModuleCreateRequestDTO;
import com.eduplatlearn.dto.module.ModuleResponseDTO;
import com.eduplatlearn.dto.module.ModuleUpdateRequestDTO;
import com.eduplatlearn.entity.Cours;
import com.eduplatlearn.entity.Module;
import com.eduplatlearn.repository.CoursRepository;
import com.eduplatlearn.repository.ModuleRepository;
import com.eduplatlearn.service.ModuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class ModuleServiceImpl implements ModuleService {
    private final ModuleRepository moduleRepository;
    private final CoursRepository coursRepository;

    public ModuleServiceImpl(ModuleRepository moduleRepository, CoursRepository coursRepository) {
        this.moduleRepository = moduleRepository;
        this.coursRepository = coursRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<ModuleResponseDTO> getAll() {
        return moduleRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ModuleResponseDTO getById(Long id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("module introuvable: " + id));
        return toResponse(module);
    }

    @Override
    public ModuleResponseDTO create(ModuleCreateRequestDTO request) {
        // Règles simples de robustesse (sans faire encore la slide Validation)
        if (request == null) {
            throw new IllegalArgumentException("ATTENTION TOUS LES CHAMPS SONT OBLIGATION ");
        }
        if (request.titre() == null || request.titre().isBlank()) {
            throw new IllegalArgumentException("titre est obligatoire");
        }
        if (request.description() == null || request.description().isBlank()) {
            throw new IllegalArgumentException("resume est obligatoire");
        }
        if (request.ordre() == null || request.ordre().describeConstable().isEmpty()) {
            throw new IllegalArgumentException("l'ordre est obligatoire");
        }


        Module module = new Module();
        applyCreate(module, request);

        // createdAt/updatedAt : idéalement gérés par JPA/Auditing (partie suivante si besoin)
        Module saved = moduleRepository.save(module );
        return toResponse(saved);
    }

    @Override
    public ModuleResponseDTO update(Long id, ModuleUpdateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("ATTENTION TOUT EST OBLIGATOIRE");
        }

        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("module introuvable: " + id));

        applyUpdate(module, request);

        // save() n'est pas strictement obligatoire si l'entité est managée,
        // mais on le garde pour être explicite pédagogiquement
        Module saved = moduleRepository.save(module);
        return toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        if (!moduleRepository.existsById(id)) {
            throw new IllegalArgumentException("module est introuvable: " + id);
        }
        moduleRepository.deleteById(id);
    }

    // ----------------------
    // MAPPING (Service Layer)
    // ----------------------

    private ModuleResponseDTO toResponse(Module module) {
        // On récupère l'objet Cours unique du module
        Cours coursDuModule = module.getCours();

        // On crée un seul DTO au lieu d'une liste
        CoursShortDTO coursShort = null;

        if (coursDuModule != null) {
            coursShort = new CoursShortDTO(
                    coursDuModule.getId(),
                    coursDuModule.getTitre()
            );
        }
        return new ModuleResponseDTO(
                module.getId(),
                module.getTitre(),
                module.getDescription(),
                module.getOrdre(),
                coursShort,
                module.getCreatedAt(),
                module.getUpdatedAt()
        );
    }

    private void applyCreate(Module module, ModuleCreateRequestDTO req) {
        module.setTitre(req.titre());
        module.setDescription(req.description());
        module.setOrdre(req.ordre());

        // On récupère l'ID unique du cours
        if (req.cours() != null) {
            // On cherche l'unique entité Cours
            Cours cours = coursRepository.findById(req.cours())
                    .orElseThrow(() -> new RuntimeException("Cours non trouvé avec l'ID : " + req.cours()));

            // On affecte l'objet seul au module
            module.setCours(cours);
        }
    }

    private void applyUpdate(Module module, ModuleUpdateRequestDTO req) {
        // Ici on fait un update "complet" (PUT) : on remplace les champs.
        // Si tu veux du PATCH plus tard, on gérera différemment.
        module.setTitre(req.titre());
        module.setDescription(req.description());
        module.setOrdre(req.ordre());
    }
}
