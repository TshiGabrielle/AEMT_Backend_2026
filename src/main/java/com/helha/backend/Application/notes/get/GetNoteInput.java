package com.helha.backend.Application.notes.get;

public record GetNoteInput(
        long id,    // id de la note
        long userId // id de l'utilisateur qui fait la demande
) {
}
