package com.eduplatlearn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;

/**
 * Classe représentant une ressource de type Vidéo.
 * Hérite de la classe abstraite Ressources et utilise la stratégie JOINED.
 * La table "video" sera liée à la table "ressources" par une clé étrangère.
 */

@Entity // Indique que cette classe est une entité JPA (sera persistée en BDD)

@Table(name = "video") // Spécifie le nom de la table associée

public class Video extends Ressources{ // Héritage de la classe parente Ressources

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private String url;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private Integer dureeSecondes;

    // ========== CONSTRUCTEURS ==========

    /**
     * Constructeur principal avec tous les paramètres nécessaires.
     * Appelle le constructeur de la classe parente pour initialiser le titre.
     *
     * @param titre Le titre de la vidéo (transmis à Ressources)
     * @param url L'URL de la vidéo
     * @param dureeSecondes La durée en secondes
     */
    public Video(String titre,Lecon lecon, String url, Integer dureeSecondes) {
        super(titre , lecon);
        this.url = url;
        this.dureeSecondes = dureeSecondes;
    }

    /** Constructeur par défaut obligatoire pour JPA */
    public Video() {
    }

    // ========== GETTERS ET SETTERS ==========
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDureeSecondes() {
        return dureeSecondes;
    }

    public void setDureeSecondes(Integer dureeSecondes) {
        this.dureeSecondes = dureeSecondes;
    }
}
