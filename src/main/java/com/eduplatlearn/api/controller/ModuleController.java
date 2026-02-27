package com.eduplatlearn.api.controller;


import com.eduplatlearn.dto.module.ModuleCreateRequestDTO;
import com.eduplatlearn.dto.module.ModuleResponseDTO;
import com.eduplatlearn.dto.module.ModuleUpdateRequestDTO;
import com.eduplatlearn.service.ModuleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/module")
public class ModuleController {
        private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }


    @GetMapping
        public List<ModuleResponseDTO> getAll() {
            return moduleService.getAll();
        }

        @GetMapping("/{id}")
        public ModuleResponseDTO getById(@PathVariable Long id) {
            return moduleService.getById(id);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public ModuleResponseDTO create( @Valid @RequestBody ModuleCreateRequestDTO request) {
            return moduleService.create(request);
        }

        @PutMapping("/{id}")
        public ModuleResponseDTO update(@Valid @PathVariable Long id,
                                       @RequestBody ModuleUpdateRequestDTO request) {
            return moduleService.update(id, request);
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void delete(@PathVariable Long id) {
            moduleService.delete(id);
        }

}
