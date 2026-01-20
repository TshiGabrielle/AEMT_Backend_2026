package com.helha.backend.Application.notes.get;

import com.helha.backend.Infrastructure.folder.DbFolder;

import java.time.LocalDateTime;

public record GetNoteOutput
        (long id,
         String name,
         String content_markdown,
         String content_html,
         Long folderId,
         LocalDateTime created_at,
         LocalDateTime updated_at,
         int taille_octet,
         int nblines,
         int nbmots,
         int nbcaract)
{}
