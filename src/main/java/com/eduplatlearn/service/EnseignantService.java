package com.eduplatlearn.service;

import com.eduplatlearn.dto.cours.CoursCreateRequestDTO;
import com.eduplatlearn.dto.cours.CoursResponseDTO;
import com.eduplatlearn.dto.cours.CoursUpdateRequestDTO;
import com.eduplatlearn.dto.enseignant.EnseignantCreateRequestDTO;
import com.eduplatlearn.dto.enseignant.EnseignantResponseDTO;
import com.eduplatlearn.dto.enseignant.EnseignantUpdateRequestDTO;

import java.util.List;

public interface EnseignantService {
    List<EnseignantResponseDTO> getAll();

    EnseignantResponseDTO getById(Long id);

    EnseignantResponseDTO create(EnseignantCreateRequestDTO request);

    EnseignantResponseDTO update(Long id, EnseignantUpdateRequestDTO request);

    void delete(Long id);
}
