package com.eduplatlearn.service.impl;

import com.eduplatlearn.entity.Cours;
import com.eduplatlearn.dto.cours.CoursCreateRequestDTO;
import com.eduplatlearn.dto.cours.CoursResponseDTO;
import com.eduplatlearn.dto.cours.CoursUpdateRequestDTO;
import com.eduplatlearn.repository.CoursRepository;
import com.eduplatlearn.service.CoursService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // toutes les méthodes sont transactionnelles par défaut
public class CoursServiceImpl implements CoursService {

    private final CoursRepository coursRepository;

    public CoursServiceImpl(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoursResponseDTO> getAll() {
        return coursRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CoursResponseDTO getById(Long id) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found: " + id));
        return toResponse(cours);
    }

    @Override
    public CoursResponseDTO create(CoursCreateRequestDTO request) {
        // Règles simples de robustesse (sans faire encore la slide Validation)
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (request.titre() == null || request.titre().isBlank()) {
            throw new IllegalArgumentException("titre is required");
        }
        if (request.niveau() == null || request.niveau().isBlank()) {
            throw new IllegalArgumentException("niveau is required");
        }

        Cours cours = new Cours();
        applyCreate(cours, request);

        // createdAt/updatedAt : idéalement gérés par JPA/Auditing (partie suivante si besoin)
        Cours saved = coursRepository.save(cours);
        return toResponse(saved);
    }

    @Override
    public CoursResponseDTO update(Long id, CoursUpdateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found: " + id));

        applyUpdate(cours, request);

        // save() n'est pas strictement obligatoire si l'entité est managée,
        // mais on le garde pour être explicite pédagogiquement
        Cours saved = coursRepository.save(cours);
        return toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        if (!coursRepository.existsById(id)) {
            throw new IllegalArgumentException("Cours not found: " + id);
        }
        coursRepository.deleteById(id);
    }

    // ----------------------
    // MAPPING (Service Layer)
    // ----------------------

    private CoursResponseDTO toResponse(Cours cours) {
        return new CoursResponseDTO(
                cours.getId(),
                cours.getTitre(),
                cours.getDescription(),
                cours.getNiveau(),
                cours.getPublie(),
                cours.getCreatedAt(),
                cours.getUpdatedAt()
        );
    }

    private void applyCreate(Cours cours, CoursCreateRequestDTO req) {
        cours.setTitre(req.titre());
        cours.setDescription(req.description());
        cours.setNiveau(req.niveau());

        // Valeur par défaut "publie = false" si non fourni
        cours.setPublie(req.publie() != null ? req.publie() : Boolean.FALSE);
    }

    private void applyUpdate(Cours cours, CoursUpdateRequestDTO req) {
        // Ici on fait un update "complet" (PUT) : on remplace les champs.
        // Si tu veux du PATCH plus tard, on gérera différemment.
        cours.setTitre(req.titre());
        cours.setDescription(req.description());
        cours.setNiveau(req.niveau());
        cours.setPublie(req.publie());
    }
}