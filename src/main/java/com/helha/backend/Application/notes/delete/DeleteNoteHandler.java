package com.helha.backend.Application.notes.delete;


import com.helha.backend.Infrastructure.note.INoteRepository;
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

        //Si la note est vide, on retourne un false
        if (note.isEmpty()) {
            return new DeleteNoteOutput(false);
        }

        //Note supprimée, on retourne un true
        noteRepository.delete(note.get());
        return new DeleteNoteOutput(true);
    }
}