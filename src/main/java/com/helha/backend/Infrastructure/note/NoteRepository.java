package com.helha.backend.Infrastructure.note;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class NoteRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String content_markdown;
    private String content_html;

    private long idFolder;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    private int taille_octet;
    private int nblines;
    private int nbmots;
    private int nbcaract;

    //Constructeur vide pour JPA
    public NoteRepository() {
    }

    //Constructeur
    public NoteRepository(String name, String content_markdown, String content_html, int idFolder) {
        this.name = name;
        this.content_markdown = content_markdown;
        this.content_html = "";
        this.idFolder = 0;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.taille_octet = 0;
        this.nblines = 0;
        this.nbmots = 0;
        this.nbcaract = 0;
    }

    // ---------------- Getters ----------------
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent_markdown() {
        return content_markdown;
    }

    public String getContent_html() {
        return content_html;
    }

    public long getIdFolder() {
        return idFolder;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public int getTaille_octet() {
        return taille_octet;
    }

    public int getNblines() {
        return nblines;
    }

    public int getNbmots() {
        return nbmots;
    }

    public int getNbcaract() {
        return nbcaract;
    }

    // ---------------- Setters ----------------
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent_markdown(String content_markdown) {
        this.content_markdown = content_markdown;
    }

    public void setContent_html(String content_html) {
        this.content_html = content_html;
    }

    public void setIdFolder(long idFolder) {
        this.idFolder = idFolder;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public void setTaille_octet(int taille_octet) {
        this.taille_octet = taille_octet;
    }

    public void setNblines(int nblines) {
        this.nblines = nblines;
    }

    public void setNbmots(int nbmots) {
        this.nbmots = nbmots;
    }

    public void setNbcaract(int nbcaract) {
        this.nbcaract = nbcaract;
    }


}
