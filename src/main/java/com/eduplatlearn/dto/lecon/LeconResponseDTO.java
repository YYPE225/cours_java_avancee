package com.eduplatlearn.dto.lecon;

import java.time.LocalDateTime;

public record LeconResponseDTO (Long id,
                                String titre,
                                String resume,
                                Integer ordre,
                                Integer dureeMinutes,
                                LocalDateTime createdAt,
                                LocalDateTime updatedAt){
}
