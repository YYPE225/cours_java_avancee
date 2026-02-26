package com.eduplatlearn.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant l'entité Module dans l'application EduPlatLearn.
 * Cette classe est mappée à la table "module" en base de données.
 */
@Entity // Indique que cette classe est une entité JPA (sera persistée en BDD)
@Table(name = "module") // Spécifie le nom de la table associée
public class Module {
    // ========== CHAMPS DE LA BASE DE DONNÉES ==========
    @Id // Clé primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation gérée par la BDD
    private Long id;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private String titre;

    @Column(length = 2000) // Limite la taille à 2000 caractères (VARCHAR(2000) en BDD)
    private String description;

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private Integer ordre;

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
    //COTE PROPRIETAIRE AVEC COURS

    @ManyToOne(optional = false) //creation de clé etrangere cours dans module
    @JoinColumn(name = "cours_id" , nullable = false) //Champ obligatoire (NOT NULL en BDD)
    private Cours cours;

    //===========RELATION 1-N ===========
    //COTE INVERSE AVEC MODULE
    @OneToMany(mappedBy = "module") //creation du champ lecon dans module
    private List<Lecon> lecons = new ArrayList<>();

    // ========== CONSTRUCTEURS ==========



    /** Constructeur par défaut obligatoire pour JPA */
    public Module() {
    }

    /**
     * Constructeur avec paramètres principaux.
     * Note : les dates ne sont pas initialisées ici.
     */
    public Module(String titre, String description, Integer ordre) {
        this.titre = titre;
        this.description = description;
        this.ordre = ordre;
    }



    // ========== GETTERS ET SETTERS ==========

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public List<Lecon> getModules() {
        return lecons;
    }
}
