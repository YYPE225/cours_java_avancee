package com.eduplatlearn.dto.enseignant;

import com.eduplatlearn.dto.cours.CoursRequest;

import java.util.List;

public record EnseignantRequest(String prenom,
                                String nom,
                                String email,
                                String bio,
                                List<Long>cours_id
                                ) {
}
