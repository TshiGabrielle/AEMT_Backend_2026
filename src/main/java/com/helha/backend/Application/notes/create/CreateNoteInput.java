package com.helha.backend.Application.notes.create;

public record CreateNoteInput (
        String name,
        String content_markdown,
        long idFolder //null = note à la racine
) {}


