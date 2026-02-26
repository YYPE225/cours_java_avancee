package com.eduplatlearn.dto.cours;

public record CoursRequest(String titre,
                           String description,
                           String niveau,
                           boolean publie) {
}
