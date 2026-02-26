package com.eduplatlearn.service;

import com.eduplatlearn.dto.cours.CoursUpdateRequestDTO;
import com.eduplatlearn.dto.lecon.LeconCreateRequestDTO;
import com.eduplatlearn.dto.lecon.LeconResponseDTO;
import com.eduplatlearn.dto.lecon.LeconUpdateRequestDTO;

import java.util.List;

public interface LeconService {
    List<LeconResponseDTO> getAll();

    LeconResponseDTO getById(Long id);

    LeconResponseDTO create(LeconCreateRequestDTO request);

    LeconResponseDTO update(Long id, LeconUpdateRequestDTO request);

    void delete(Long id);
}
