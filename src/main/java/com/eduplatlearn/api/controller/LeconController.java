package com.eduplatlearn.api.controller;

import com.eduplatlearn.dto.lecon.LeconCreateRequestDTO;
import com.eduplatlearn.dto.lecon.LeconResponseDTO;
import com.eduplatlearn.dto.lecon.LeconUpdateRequestDTO;
import com.eduplatlearn.service.LeconService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/lecon")
public class LeconController {
    private final LeconService leconService;

    public LeconController(LeconService leconService) {
        this.leconService = leconService;
    }

    @GetMapping
    public List<LeconResponseDTO> getAll() {
        return leconService.getAll();
    }

    @GetMapping("/{id}")
    public LeconResponseDTO getById(@PathVariable Long id) {
        return leconService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeconResponseDTO create( @Valid @RequestBody LeconCreateRequestDTO request) {
        return leconService.create(request);
    }

    @PutMapping("/{id}")
    public LeconResponseDTO update(@Valid @PathVariable Long id,
                                   @RequestBody LeconUpdateRequestDTO request) {
        return leconService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        leconService.delete(id);
    }
}
