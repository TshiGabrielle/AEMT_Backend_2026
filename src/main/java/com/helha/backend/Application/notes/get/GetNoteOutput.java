package com.helha.backend.Application.notes.get;

import java.time.LocalDateTime;

public record GetNoteOutput
        (long id,
         String name,
         String content_markdown,
         String content_html,
         long idFolder,
         LocalDateTime created_at,
         LocalDateTime updated_at,
         int taille_octet,
         int nblines,
         int nbmots,
         int nbcaract)
{}
