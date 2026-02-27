package com.eduplatlearn.dto.enseignant;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record EnseignantCreateRequestDTO(
        @NotBlank(message = "Le prenom est obligatoire")
        @Size(min =2, max = 100, message = "Le prenom doit contenir entre 3 et 100 caractères")
        String prenom,

        @NotBlank(message = "Le nom est obligatoire")
        @Size(min = 1, max = 10, message = "Le nom doit contenir entre 1 et 10 caractères")
        String nom,

        @NotBlank(message = "L'email est obligatoire")
        @Size(min = 2, max = 15, message = "L'email doit contenir entre 15 et 10 caractères")
        String email,

        @NotBlank(message = "Le bio est obligatoire")
        @Size(min = 2, max = 20, message = "Le bio doit contenir entre 10 et 20 caractères")
        String bio,

        //liste des cours
        List<Long>cours
                ) {
}
