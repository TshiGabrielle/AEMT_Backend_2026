package com.helha.backend.Application.notes.delete;

public record DeleteNoteInput (
        long id,    // id de la note à supprimer
        long userId // id de l'utilisateur qui fait la demande
){}
