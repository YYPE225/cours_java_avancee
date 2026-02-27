package com.eduplatlearn.dto.lecon;

import jakarta.validation.constraints.*;

public record LeconCreateRequestDTO(
        @NotBlank(message = "Le titre est obligatoire")
        @Size(min =3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères")
        String titre,

        @NotBlank(message = "Le resume est obligatoire")
        @Size(min =3, max = 100, message = "Le resume doit contenir entre 3 et 100 caractères")
        String resume,

        @NotNull(message = "L'ordre est obligatoire")
        @Min(value = 1, message = "L'ordre doit être au minimum 1")
        @Max(value = 100, message = "L'ordre ne peut pas dépasser 100")
        Integer ordre,

        @NotNull(message = "dureeMinutes est obligatoire")
        @Min(value = 1, message = "dureeMinutes doit être au minimum 1")
        @Max(value = 100, message = "dureeMinutes ne peut pas dépasser 100")
        Integer dureeMinutes,

        @NotNull(message = "Le cours ne dois jamais etre vide")
        Long module                        ){
}
