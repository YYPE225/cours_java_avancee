package com.eduplatlearn.api.controller;

import com.eduplatlearn.dto.cours.CoursCreateRequestDTO;
import com.eduplatlearn.dto.cours.CoursResponseDTO;
import com.eduplatlearn.dto.cours.CoursUpdateRequestDTO;
import com.eduplatlearn.service.CoursService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/cours")
public class CoursController {

    private final CoursService coursService;

    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }

    @GetMapping
    public List<CoursResponseDTO> getAll() {
        return coursService.getAll();
    }

    @GetMapping("/{id}")
    public CoursResponseDTO getById(@PathVariable Long id) {
        return coursService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CoursResponseDTO create( @Valid @RequestBody CoursCreateRequestDTO request) {
        return coursService.create(request);
    }

    @PutMapping("/{id}")
    public CoursResponseDTO update(@Valid @PathVariable Long id,
                                   @RequestBody CoursUpdateRequestDTO request) {
        return coursService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        coursService.delete(id);
    }
}