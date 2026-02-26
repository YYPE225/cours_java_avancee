package com.eduplatlearn.service.impl;


import com.eduplatlearn.dto.enseignant.EnseignantCreateRequestDTO;
import com.eduplatlearn.dto.enseignant.EnseignantResponseDTO;
import com.eduplatlearn.dto.enseignant.EnseignantUpdateRequestDTO;
import com.eduplatlearn.entity.Enseignant;
import com.eduplatlearn.repository.EnseignantRepository;
import com.eduplatlearn.service.EnseignantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class EnseignantServiceImpl implements EnseignantService {

    private final EnseignantRepository enseignantRepository;

    public EnseignantServiceImpl(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnseignantResponseDTO> getAll() {
        return enseignantRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EnseignantResponseDTO getById(Long id) {
        Enseignant enseignant = enseignantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("enseignant introuvable: " + id));
        return toResponse(enseignant);
    }

    @Override
    public EnseignantResponseDTO create(EnseignantCreateRequestDTO request) {
        // Règles simples de robustesse (sans faire encore la slide Validation)
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }
        if (request.prenom() == null || request.prenom().isBlank()) {
            throw new IllegalArgumentException("titre is required");
        }
        if (request.nom() == null || request.nom().isBlank()) {
            throw new IllegalArgumentException("niveau is required");
        }

        Enseignant enseignant = new Enseignant();
        applyCreate(enseignant, request);

        // createdAt/updatedAt : idéalement gérés par JPA/Auditing (partie suivante si besoin)
        Enseignant saved = enseignantRepository.save(enseignant );
        return toResponse(saved);
    }

    @Override
    public EnseignantResponseDTO update(Long id, EnseignantUpdateRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        Enseignant enseignant = enseignantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found: " + id));

        applyUpdate(enseignant, request);

        // save() n'est pas strictement obligatoire si l'entité est managée,
        // mais on le garde pour être explicite pédagogiquement
        Enseignant saved = enseignantRepository.save(enseignant);
        return toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        if (!enseignantRepository.existsById(id)) {
            throw new IllegalArgumentException("Cours not found: " + id);
        }
        enseignantRepository.deleteById(id);
    }

    // ----------------------
    // MAPPING (Service Layer)
    // ----------------------

    private EnseignantResponseDTO toResponse(Enseignant enseignant) {
        return new EnseignantResponseDTO(
                enseignant.getId(),
                enseignant.getPrenom(),
                enseignant.getNom(),
                enseignant.getBio(),
                enseignant.getEmail(),
                enseignant.getCreatedAt(),
                enseignant.getUpdatedAt()
        );
    }

    private void applyCreate(Enseignant enseignant, EnseignantCreateRequestDTO req) {
        enseignant.setNom(req.nom());
        enseignant.setPrenom(req.prenom());
        enseignant.setEmail(req.email());
        enseignant.setBio(req.bio());
    }

    private void applyUpdate(Enseignant enseignant, EnseignantUpdateRequestDTO req) {
        // Ici on fait un update "complet" (PUT) : on remplace les champs.
        // Si tu veux du PATCH plus tard, on gérera différemment.
        enseignant.setNom(req.nom());
        enseignant.setPrenom(req.prenom());
        enseignant.setEmail(req.email());
        enseignant.setBio(req.bio());
    }
}
