package com.eduplatlearn.service;

import com.eduplatlearn.dto.cours.*;
import com.eduplatlearn.entity.Cours;
import com.eduplatlearn.repository.CoursRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursServiceimpl implements CoursService{
    private final CoursRepository coursRepository;

    public CoursServiceimpl(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }


    @Override
    public List<CoursView> getAll() {
        return coursRepository.findAll().stream().map(this::toView).toList();
    }

    @Override
    public CoursView getById(Long id) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found: " + id));
        return toView(cours);
    }

    @Override
    public CoursView create(CoursRequest request) {
        Cours cours = new Cours();
        cours.setTitre(request.titre());
        cours.setDescription(request.description());
        cours.setNiveau(request.niveau());
        cours.setPublie(request.publie());
        return toView(coursRepository.save(cours));
    }

    @Override
    public CoursView update(Long id, CoursRequest request) {
        Cours cours = coursRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found: " + id));
        cours.setTitre(request.titre());
        return toView(coursRepository.save(cours));
    }
    private CoursView toView(Cours cours) {
        return new CoursView(cours.getId(), cours.getTitre(), cours.getDescription(),cours.getNiveau(), cours.getPublie());
    }
}
