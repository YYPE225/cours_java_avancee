package com.eduplatlearn.entity;

import jakarta.persistence.*;

/**
 * Classe représentant une ressource de type Vidéo.
 * Hérite de la classe abstraite Ressources et utilise la stratégie JOINED.
 * La table "texte" sera liée à la table "ressources" par une clé étrangère.
 */

@Entity // Indique que cette classe est une entité JPA (sera persistée en BDD)

@Table(name = "texte") // Spécifie le nom de la table associée

public class texte extends Ressources{ // Héritage de la classe parente Ressources

    @Lob //stocker du contenu
    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private String contenu;

    // ========== CONSTRUCTEURS ==========

    /** Constructeur par défaut obligatoire pour JPA */
    public texte() {
    }

    /**
     * Constructeur principal avec tous les paramètres nécessaires.
     * Appelle le constructeur de la classe parente pour initialiser le titre.
     *
     * @param titre Le titre du texte (transmis à Ressources)
     * @param lecon la lecon lié à ressources (transmis à Ressources)
     * @param contenu Le contenu du texte
     */
    public texte(String titre,Lecon lecon, String contenu) {
        super(titre,lecon);
        this.contenu = contenu;
    }

    // ========== GETTERS ET SETTERS ==========
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
