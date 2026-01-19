package com.helha.backend.Application.notes.get;

import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetNoteHandler {
    private final INoteRepository noteRepository;

    public GetNoteHandler(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public GetNoteOutput handle(GetNoteInput input) {

        Optional<NoteRepository> optionalNote =
                noteRepository.findById(input.id());

        if (optionalNote.isEmpty()) {
            return null; // ou exception plus tard
        }

        NoteRepository note = optionalNote.get();

        return new GetNoteOutput(
                note.getId(),
                note.getName(),
                note.getContent_markdown(),
                note.getContent_html(),
                note.getIdFolder(),
                note.getCreated_at(),
                note.getUpdated_at(),
                note.getTaille_octet(),
                note.getNblines(),
                note.getNbmots(),
                note.getNbcaract()
        );
    }
}
