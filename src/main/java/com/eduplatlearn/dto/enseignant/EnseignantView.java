package com.eduplatlearn.dto.enseignant;

public record EnseignantView(
        Long id,
        String prenom,
        String nom,
        String email,
        String bio) {
}
