package com.eduplatlearn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Classe représentant une ressource de type Vidéo.
 * Hérite de la classe abstraite Ressources et utilise la stratégie JOINED.
 * La table "fichier" sera liée à la table "ressources" par une clé étrangère.
 */

@Entity // Indique que cette classe est une entité JPA (sera persistée en BDD)

@Table(name = "fichier") // Spécifie le nom de la table associée
public class Fichier extends Ressources { // Héritage de la classe parente Ressources


    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private String nomfichier;

    @Column(nullable = false) // Champ obligatoire (NOT NULL en BDD)
    private String cheminStockage;


    // ========== CONSTRUCTEURS ==========
    /** Constructeur par défaut obligatoire pour JPA */
    public Fichier() {
    }

    /**
     * Constructeur principal avec tous les paramètres nécessaires.
     * Appelle le constructeur de la classe parente pour initialiser le titre.
     *
     * @param titre Le titre du texte (transmis à Ressources.)
     * @param lecon la lecon (transmis à Ressources.)
     * @param nomfichier Le nom du fichier
     * @param cheminStockage , le chemin de stockage
     */
    public Fichier(String titre,Lecon lecon, String nomfichier, String cheminStockage) {
        super(titre , lecon);
        this.nomfichier = nomfichier;
        this.cheminStockage = cheminStockage;
    }

    // ========== GETTERS ET SETTERS ==========
    public String getNomfichier() {
        return nomfichier;
    }

    public void setNomfichier(String nomfichier) {
        this.nomfichier = nomfichier;
    }

    public String getCheminStockage() {
        return cheminStockage;
    }

    public void setCheminStockage(String cheminStockage) {
        this.cheminStockage = cheminStockage;
    }
}
