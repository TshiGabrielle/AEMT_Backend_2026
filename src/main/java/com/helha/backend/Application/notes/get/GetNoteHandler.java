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
        // on récupère la note uniquement si elle appartient à l'utilisateur
        Optional<NoteRepository> optionalNote =
                noteRepository.findByIdAndUserId(input.id(), input.userId());

        if (optionalNote.isEmpty()) {
            return null;
        }

        NoteRepository note = optionalNote.get();

        Long folderId = null;
        if (note.getFolder() != null) {
            folderId = note.getFolder().getId();
        }


        return new GetNoteOutput(
                note.getId(),
                note.getName(),
                note.getContent_markdown(),
                note.getContent_html(),
                folderId,
                note.getCreated_at(),
                note.getUpdated_at(),
                note.getTaille_octet(),
                note.getNblines(),
                note.getNbmots(),
                note.getNbcaract()
        );
    }
}
