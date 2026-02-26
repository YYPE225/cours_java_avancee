package com.eduplatlearn.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Classe abstraite représentant une ressource pédagogique dans l'application.
 * Cette classe sert de base pour différents types de ressources (vidéos, PDF, quiz, etc.)
 * en utilisant une stratégie d'héritage JOINED.
 */

@Entity // Indique que cette classe est une entité JPA (sera persistée en BDD)

@Table(name = "ressources") // Spécifie le nom de la table associée

@Inheritance(strategy = InheritanceType.JOINED) // Stratégie : une table par classe fille + table parente commune

@DiscriminatorColumn(// Colonne qui permet d'identifier le type de ressource (utilisée par JPA)
        name = "type_ressource"  ,
        // Nom de la colonne discriminante
        discriminatorType = DiscriminatorType.STRING )// Type de donnée (STRING, INTEGER, CHAR)

public abstract class Ressources { // Classe abstraite, car on ne peut pas instancier une ressource "générique"

    // ========== CHAMPS COMMUNS À TOUTES LES RESSOURCES ==========

    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation gérée par la BDD
    private Long id;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private String titre;

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

    //==========RELATION 1-1 ============
    //COTE PROPRIETAIRE AVEC LECON
    @OneToOne(optional = false)
    @JoinColumn(name = "lecon_id", nullable = false , unique = true)
    private Lecon lecon;


    // ========== CONSTRUCTEURS ==========

    /**
     * Constructeur avec le titre et la lecon.
     * Les dates ne sont pas initialisées ici - à gérer avec @PrePersist
     */
    protected Ressources(String titre, Lecon lecon) {
        this.titre = titre;
        this.lecon = lecon;
    }

    /** Constructeur par défaut obligatoire pour JPA */
    public Ressources() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Lecon getLecon() {
        return lecon;
    }

    public void setLecon(Lecon lecon) {
        this.lecon = lecon;
    }
}
