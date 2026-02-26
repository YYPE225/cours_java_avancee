package com.eduplatlearn.api.controller;

import com.eduplatlearn.dto.cours.CoursRequest;
import com.eduplatlearn.dto.cours.CoursResponseDTO;
import com.eduplatlearn.dto.cours.CoursView;
import com.eduplatlearn.entity.Cours;
import com.eduplatlearn.service.CoursService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

    private final CoursService coursService;


    public CoursController(CoursService coursService){
        this.coursService = coursService;
    }

    @GetMapping
    public List<CoursView> getAll(){
        return coursService.getAll();
    }

    @GetMapping("/{id}")
    public List<CoursView> getByid(@PathVariable Long id){
        return Collections.singletonList(coursService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CoursView create(@RequestBody CoursRequest request) {
        return coursService.create(request);
    }
}
