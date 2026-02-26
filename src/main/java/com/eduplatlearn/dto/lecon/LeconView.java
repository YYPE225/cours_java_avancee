package com.eduplatlearn.dto.lecon;

public record LeconView(Long id,
                        String titre,
                        String resume,
                        Integer ordre,
                        Integer dureeMinutes) {
}
