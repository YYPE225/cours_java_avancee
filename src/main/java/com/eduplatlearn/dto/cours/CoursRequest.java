package com.eduplatlearn.dto.cours;

public record CoursRequest(Long id,
                           String titre,
                           String description,
                           String niveau,
                           boolean publie) {
}
