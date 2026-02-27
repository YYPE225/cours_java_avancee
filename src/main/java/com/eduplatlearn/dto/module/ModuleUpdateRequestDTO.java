package com.eduplatlearn.dto.module;

import com.eduplatlearn.dto.cours.CoursShortDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ModuleUpdateRequestDTO(@NotBlank(message = "Le titre est obligatoire")
                                     @Size(min =3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères")
                                     String titre,

                                     @NotBlank(message = "Le titre est obligatoire")
                                     @Size(min =3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères")
                                     String description,

                                     @NotNull(message = "le champ ne doit pas etre vide ")
                                     Integer ordre,

                                     @NotNull(message = "le champ ne doit pas etre vide")
                                     Long cours) {
}
