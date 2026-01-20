package com.helha.backend.Infrastructure.note;

import com.helha.backend.Infrastructure.folder.DbFolder;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class NoteRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    
    @Column(name = "content_markdown", columnDefinition = "TEXT")
    private String content_markdown;
    
    @Column(name = "content_html", columnDefinition = "TEXT")
    private String content_html;

    @ManyToOne(optional=true)
    @JoinColumn(name = "folder_id")
    private DbFolder folder;
    
    @Column(name = "created_at")
    private LocalDateTime created_at;
    
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Column(name = "taille_octet")
    private int taille_octet;
    
    @Column(name = "nblines")
    private int nblines;
    
    @Column(name = "nbmots")
    private int nbmots;
    
    @Column(name = "nbcaract")
    private int nbcaract;

    // Constructeur vide pour JPA
    public NoteRepository() {
    }

    // Constructeur
    public NoteRepository(String name, String content_markdown, String content_html, DbFolder folder) {
        this.name = name;
        this.content_markdown = content_markdown;
        this.content_html = content_html != null ? content_html : "";
        this.folder = folder;
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

    public DbFolder getFolder() {
        return folder;
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

    public void setFolder(DbFolder folder) {
        this.folder = folder;
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
