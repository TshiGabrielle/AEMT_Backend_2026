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

        //retourne null si note est vide
        if (optionalNote.isEmpty()) {
            return null;
        }
        // On a trouvé la note, on la récupère.
        NoteRepository note = optionalNote.get();

        // On extrait l'id du dossier si la note est rattachée à un dossier ; sinon, on laisse null
        Long folderId = null;
        if (note.getFolder() != null) {
            folderId = note.getFolder().getId();
        }

        //On retourne objet de sortie avec les infos nécessaires pour le frontend
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
