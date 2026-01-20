package com.helha.backend.Application.notes.delete;

import com.helha.backend.Application.notes.create.CreateNoteHandler;
import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteNoteHandler {

    private final INoteRepository noteRepository;

    public DeleteNoteHandler(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public DeleteNoteOutput handle(DeleteNoteInput input) {

        // Vérifie que la note existe ET appartient à l'utilisateur
        var note = noteRepository.findByIdAndUserId(input.id(), input.userId());

        if (note.isEmpty()) {
            return new DeleteNoteOutput(false);
        }

        noteRepository.delete(note.get());
        return new DeleteNoteOutput(true);
    }
}