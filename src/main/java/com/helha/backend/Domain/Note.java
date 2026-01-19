package com.helha.backend.Domain;

import java.time.LocalDateTime;

public class Note {
    private int id;
    private String name;
    private String content_markdown;
    private String content_html;

    private int idFolder;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    private int taille_octet;
    private int nblines;
    private int nbmots;
    private int nbcaract;

}
