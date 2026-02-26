package com.eduplatlearn.dto.enseignant;

public record EnseignantRequest(String prenom,
                                String nom,
                                String email,
                                String bio) {
}
