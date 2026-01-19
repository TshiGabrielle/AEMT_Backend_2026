package com.helha.backend.Application.notes.create;

import java.time.LocalDateTime;

public record CreateNoteOutput (
        long id,
        String name,
        LocalDateTime created_at

) {}