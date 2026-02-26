package com.eduplatlearn.api.controller;


import com.eduplatlearn.dto.enseignant.EnseignantCreateRequestDTO;
import com.eduplatlearn.dto.enseignant.EnseignantResponseDTO;
import com.eduplatlearn.dto.enseignant.EnseignantUpdateRequestDTO;
import com.eduplatlearn.service.EnseignantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/enseignants")
public class EnseignantController {
    private final EnseignantService enseignantService;

    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }


    @GetMapping
    public List<EnseignantResponseDTO> getAll() {
        return enseignantService.getAll();
    }

    @GetMapping("/{id}")
    public EnseignantResponseDTO getById(@PathVariable Long id) {
        return enseignantService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnseignantResponseDTO create( @Valid @RequestBody EnseignantCreateRequestDTO request) {
        return enseignantService.create(request);
    }

    @PutMapping("/{id}")
    public EnseignantResponseDTO update(@Valid @PathVariable Long id,
                                   @RequestBody EnseignantUpdateRequestDTO request) {
        return enseignantService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        enseignantService.delete(id);
    }
}
