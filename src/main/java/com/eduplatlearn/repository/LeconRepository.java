package com.eduplatlearn.repository;

import com.eduplatlearn.entity.Lecon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pour l'entité Lecon.
 * Cette interface permet d'effectuer des opérations CRUD sur la table "cours"
 * sans avoir à écrire de code d'implémentation !
 *
 * Spring Data JPA génère automatiquement l'implémentation à l'exécution.
 */
public interface LeconRepository extends JpaRepository<Lecon,Long> {
}
