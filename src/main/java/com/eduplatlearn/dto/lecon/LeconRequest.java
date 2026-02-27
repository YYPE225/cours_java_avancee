package com.eduplatlearn.dto.lecon;

public record LeconRequest(String titre,
                           String resume,
                           Integer ordre,
                           Integer dureeMinutes,
                           long module_id) {
}
