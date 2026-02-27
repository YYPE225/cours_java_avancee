package com.eduplatlearn.dto.module;

import com.eduplatlearn.dto.cours.CoursShortDTO;

import java.time.LocalDateTime;

public record ModuleResponseDTO(Long id,
                                String titre,
                                String description,
                                Integer ordre,
                                CoursShortDTO cours,
                                LocalDateTime createdAt,
                                LocalDateTime updatedAt) {
}
