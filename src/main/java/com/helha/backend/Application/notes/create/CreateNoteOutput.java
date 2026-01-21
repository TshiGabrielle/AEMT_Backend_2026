package com.helha.backend.Application.notes.create;

import java.time.LocalDateTime;

public record CreateNoteOutput (
        long id, //id de la note créée
        String name,    //nom de la note créée
        LocalDateTime created_at    //date de la note créée

) {}