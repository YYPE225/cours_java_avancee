package com.eduplatlearn.service;


import com.eduplatlearn.dto.module.ModuleCreateRequestDTO;
import com.eduplatlearn.dto.module.ModuleResponseDTO;
import com.eduplatlearn.dto.module.ModuleUpdateRequestDTO;

import java.util.List;

public interface ModuleService {
    List<ModuleResponseDTO> getAll();

    ModuleResponseDTO getById(Long id);

    ModuleResponseDTO create(ModuleCreateRequestDTO request);

    ModuleResponseDTO update(Long id, ModuleUpdateRequestDTO request);

    void delete(Long id);
}
