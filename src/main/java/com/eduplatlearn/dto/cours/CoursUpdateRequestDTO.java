package com.eduplatlearn.dto.cours;

public record CoursUpdateRequestDTO(Long id,
                                    String titre,
                                    String description,
                                    String niveau,
                                    boolean publie) {
}
