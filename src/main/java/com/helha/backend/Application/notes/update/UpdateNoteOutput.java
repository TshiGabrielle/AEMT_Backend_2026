package com.helha.backend.Application.notes.update;

import java.time.LocalDateTime;

// données renvoyées après la modif d'une note
public record UpdateNoteOutput(
        boolean success,
        String message,
        LocalDateTime updated_at
) {}
