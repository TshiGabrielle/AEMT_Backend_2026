package com.helha.backend.Application.notes.create;

public record CreateNoteInput (
        String name,                // titre de la note
        String content_markdown,    // contenu en markdown
        Long idFolder,               //null = note à la racine
        long userId                 // id de l'utilisateur propriétaire
) {}


