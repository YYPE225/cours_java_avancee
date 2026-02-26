package com.eduplatlearn.repository;

import com.eduplatlearn.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository pour l'entité Cours.
 * Cette interface permet d'effectuer des opérations CRUD sur la table "cours"
 * sans avoir à écrire de code d'implémentation !
 *
 * Spring Data JPA génère automatiquement l'implémentation à l'exécution.
 */
public interface CoursRepository extends JpaRepository<Cours,Long> {
    @Query("select distinct c from Cours c left join fetch c.modules")
    List<Cours> findAllWithModules();

    //@Query("select distinct c from Cours c left join fetch c.enseignants")
    //List<Cours> findAllWillEnseignants();


}
