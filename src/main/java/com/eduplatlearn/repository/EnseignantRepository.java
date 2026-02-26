package com.eduplatlearn.repository;

import com.eduplatlearn.entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pour l'entité Enseignant.
 * Cette interface permet d'effectuer des opérations CRUD sur la table "cours"
 * sans avoir à écrire de code d'implémentation !
 *
 * Spring Data JPA génère automatiquement l'implémentation à l'exécution.
 */
public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {
}
