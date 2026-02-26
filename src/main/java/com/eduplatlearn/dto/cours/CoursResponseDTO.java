package com.eduplatlearn.dto.cours;

public record CoursResponseDTO(Long id,
                               String titre,
                               String description,
                               String niveau,
                               boolean publie) {

}
