package com.helha.backend.Infrastructure.user;

import jakarta.persistence.*;

/**
 * Représente l'entité de la table "users"
 * en base de données.
 */
@Entity
@Table(name = "users")
public class DbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pseudo;
    private String password;

    /**
     * Constructeur vide requis par JPA
     */
    public DbUser() {
    }

    /**
     * Constructeur pour initialiser les attributs
     */
    public DbUser(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    /**
     * Getters
     */
    public Long getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Setters
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
