package com.eduplatlearn.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant l'entité Enseignant dans l'application EduPlatLearn.
 * Cette classe est mappée à la table "enseignant" en base de données.
 */
@Entity // Indique que cette classe est une entité JPA (sera persistée en BDD)
@Table(name = "enseignant")// Spécifie le nom de la table associée
public class Enseignant {

    // ========== CHAMPS DE LA BASE DE DONNÉES ==========
    @Id  // Clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation gérée par la BDD
    private Long id;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private String prenom;

    @Column(nullable = false)// Champ obligatoire (NOT NULL en BDD)
    private String nom;

    @Column(nullable = false, unique = true)// Champ obligatoire ET doit être unique en BDD
    private String email;

    @Column(length = 1000) // Limite la taille à 1000 caractères (VARCHAR(1000) en BDD)
    private String bio;

    @Column(nullable = false, updatable = false)// Champ obligatoire et ne pourra pas être modifié
    private LocalDateTime createdAt;

    @Column(nullable = false)// Champ obligatoire (NOT NULL en BDD)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    //===========RELATION N-N ===========
    //COTE PROPRIETAIRE ENTRE ENSEIGNANT ET COURS
    @ManyToMany
    @JoinTable(//nom de la table
            name = "enseignant_cours",
            //colonne join crée du nom d'enseignant_id liée à enseignant
            joinColumns = @JoinColumn(name = "enseingnant_id"),
            //colonne join crée du nom de cours_id liée à cours
            inverseJoinColumns = @JoinColumn(name = "cours_id")
    )
    private List<Cours> cours = new ArrayList<>(); //creation de l'objet cours pour liéer à la cours

    // ========== CONSTRUCTEURS ==========

    /**
     * Constructeur avec paramètres principaux.
     * Note : les dates ne sont pas initialisées ici.
     */

    public Enseignant(String prenom, String nom, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    /** Constructeur par défaut obligatoire pour JPA */
    public Enseignant() {
    }


    // ========== GETTERS ET SETTERS ==========
    public Long getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}
