package com.eduplatlearn.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant l'entité Lecon dans l'application EduPlatLearn.
 * Cette classe est mappée à la table "cours" en base de données.
 */
@Entity // Indique que cette classe est une entité JPA (sera persistée en BDD)
@Table(name = "cours") // Spécifie le nom de la table associée
public class Cours {

    // ========== CHAMPS DE LA BASE DE DONNÉES ==========
    @Id // Clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation gérée par la BDD
    private Long id;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private String titre;

    @Column(length = 2000) // Limite la taille à 2000 caractères (VARCHAR(2000) en BDD)
    private String description;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private String niveau;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private Boolean publie = false;

    @Column(nullable = false, updatable = false) // Champ obligatoire et ne pourra pas être modifié
    private LocalDateTime createdAt;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private LocalDateTime updatedAt;

    public Cours(Long id, String titre, String description, String niveau, Boolean publie) {
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    //===========RELATION 1-N ===========
    //COTE INVERSE AVEC COURS
    @OneToMany(mappedBy = "cours") //creation du champ module dans cours
    private List<Module> modules = new ArrayList<>();

    //===========RELATION N-N ===========
    //COTE INVERSE AVEC ENSEIGNANT
    @ManyToMany(mappedBy = "cours")//creation du champ enseignant dans cours
    private List<Enseignant> enseignants = new ArrayList<>();

    // ========== CONSTRUCTEURS ==========

    /**
     * Constructeur avec paramètres principaux.
     * Note : les dates ne sont pas initialisées ici.
     */
    public Cours(String titre, String description, String niveau) {
        this.titre = titre;
        this.description = description;
        this.niveau = niveau;
    }

    /** Constructeur par défaut obligatoire pour JPA */
    public Cours() {
    }

    // ========== GETTERS ET SETTERS ==========

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public Boolean getPublie() {
        return publie;
    }

    public void setPublie(Boolean publie) {
        this.publie = publie;
    }

    public List<Module> getModules() {
        return modules;
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

    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
