package com.eduplatlearn.service;

import com.eduplatlearn.dto.cours.*;
import com.eduplatlearn.entity.Cours;

import java.util.List;

public interface CoursService {
    List<CoursView> getAll();
    CoursView getById(Long id);

    CoursView create(CoursRequest request);

    CoursView update(Long id, CoursRequest request);


}
