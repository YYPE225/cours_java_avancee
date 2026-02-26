package com.eduplatlearn.dto.enseignant;

import java.time.LocalDateTime;

public record EnseignantResponseDTO(
        Long id,
        String prenom,
        String nom,
        String email,
        String bio,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
