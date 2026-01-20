package com.helha.backend.Application.notes.list;

import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListNotesHandler {

    private final INoteRepository noteRepository;

    public ListNotesHandler(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Récupère toutes les notes appartenant à un utilisateur.
     */
    public List<ListNotesOutput> handle(long userId) {

        List<NoteRepository> notes = noteRepository.findByUserId(userId);

        return notes.stream()
                .map(note -> new ListNotesOutput(
                        note.getId(),
                        note.getName()
                ))
                .toList();
    }
}