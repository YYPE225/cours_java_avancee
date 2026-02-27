package com.eduplatlearn.dto.enseignant;

import com.eduplatlearn.dto.cours.CoursShortDTO;


import java.util.List;

public record EnseignantView(
        Long id,
        String prenom,
        String nom,
        String email,
        String bio,
        List<CoursShortDTO>cours) {
}
