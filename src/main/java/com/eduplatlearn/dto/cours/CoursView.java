package com.eduplatlearn.dto.cours;

public record CoursView(Long id,
                        String titre,
                        String description,
                        String niveau,
                        boolean publie) {
}
