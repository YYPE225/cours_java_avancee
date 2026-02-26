package com.eduplatlearn.dto.lecon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LeconUpdateRequestDTO(
        @NotBlank(message = "Le titre est obligatoire")
        @Size(min =3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères")
        String titre,

        @NotBlank(message = "Le resume est obligatoire")
        @Size(min =3, max = 100, message = "Le resume doit contenir entre 3 et 100 caractères")
        String resume,

        @NotBlank(message = "Le numero d'ordre est obligatoire")
        @Size(min =1, max = 100, message = "Le numero d'ordre doit contenir entre 1 et 100 caractères")
        Integer ordre,

        @NotBlank(message = "ce champ est obligatoire")
        @Size(min =10, max = 100, message = "il doit contenir entre 10 et 100 caractères")
        Integer dureeMinutes) {
}
