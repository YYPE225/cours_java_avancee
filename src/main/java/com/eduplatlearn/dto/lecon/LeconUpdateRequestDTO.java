package com.eduplatlearn.dto.lecon;

import jakarta.validation.constraints.*;

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

        @NotNull(message = "dureeMinutes est obligatoire")
        @Min(value = 1, message = "dureeMinutes doit être au minimum 1")
        @Max(value = 100, message = "dureeMinutes ne peut pas dépasser 100")
        Integer dureeMinutes,

        @NotNull(message = "Le cours ne dois jamais etre vide")
        Long module    ) {
}
