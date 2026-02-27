package com.eduplatlearn.dto.lecon;

import com.eduplatlearn.dto.module.ModuleShortDTO;

public record LeconView(Long id,
                        String titre,
                        String resume,
                        Integer ordre,
                        Integer dureeMinutes,
                        ModuleShortDTO module) {
}
