package com.eduplatlearn.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant l'entité Lecon dans l'application EduPlatLearn.
 * Cette classe est mappée à la table "lecon" en base de données.
 */
@Entity // Indique que cette classe est une entité JPA (sera persistée en BDD)
@Table(name = "lecon") // Spécifie le nom de la table associée
public class Lecon {
    // ========== CHAMPS DE LA BASE DE DONNÉES ==========
    @Id // Clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation gérée par la BDD
    private Long id;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private String titre;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private String resume;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private Integer ordre;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private Integer dureeMinutes;

    @Column(nullable = false, updatable = false) // Champ obligatoire et ne pourra pas être modifié
    private LocalDateTime createdAt;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
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

    //===========RELATION 1-N ===========
    //COTE PROPRIETAIRE AVEC MODULE
    @ManyToOne(optional = false) //creation de la cle etrangere dans lecon
    @JoinColumn(name = "module_id", nullable = false) //Champ obligatoire (NOT NULL en BDD)
    private Module module;

    //==========RELATION 1-1 ============
    //COTE INVERSE AVEC RESSOUCES
    @OneToOne(mappedBy = "lecon")
    private Ressources ressources;

    // ========== CONSTRUCTEURS ==========

    /** Constructeur par défaut obligatoire pour JPA */
    public Lecon() {
    }

    /**
     * Constructeur avec paramètres principaux.
     * Note : les dates ne sont pas initialisées ici.
     */
    public Lecon(String titre, String resume) {
        this.titre = titre;
        this.resume = resume;
    }


    // ========== GETTERS ET SETTERS ==========

    public Long getId() {
        return id;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public Integer getDureeMinutes() {
        return dureeMinutes;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public void setDureeMinutes(Integer dureeMinutes) {
        this.dureeMinutes = dureeMinutes;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setRessources(Ressources ressources) {
        this.ressources = ressources;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Ressources getRessources() {
        return ressources;
    }
}
