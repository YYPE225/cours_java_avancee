package com.eduplatlearn.dto.enseignant;


import com.eduplatlearn.dto.cours.CoursShortDTO;

import java.time.LocalDateTime;
import java.util.List;

public record EnseignantResponseDTO(
        Long id,
        String prenom,
        String nom,
        String email,
        String bio,
        List<CoursShortDTO> cours, // <-- Liste d'objets ID + Titre,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
