package com.helha.backend.Application.notes.update;

// données nécessaires pour modifier une note
public record UpdateNoteInput(
        long id,                 // id de la note à modifier
        String name,             // titre (ou nouveau titre)
        String contentMarkdown   // nouveau contenu en markdown
) {}