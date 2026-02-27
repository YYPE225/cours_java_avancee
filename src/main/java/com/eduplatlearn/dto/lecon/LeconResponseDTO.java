package com.eduplatlearn.dto.lecon;

import com.eduplatlearn.dto.module.ModuleShortDTO;

import java.time.LocalDateTime;

public record LeconResponseDTO (Long id,
                                String titre,
                                String resume,
                                Integer ordre,
                                Integer dureeMinutes,
                                ModuleShortDTO module, // <-- Liste d'objets ID + Titre, cas @ManytoOne
                                LocalDateTime createdAt,
                                LocalDateTime updatedAt){
}
