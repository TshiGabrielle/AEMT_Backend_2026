package com.helha.backend.Application.notes.update;

// données renvoyées après la modif d'une note
public record UpdateNoteOutput(
        boolean success,
        String message
) {}
