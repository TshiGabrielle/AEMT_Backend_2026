package com.helha.backend.Application.notes.update;

import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

// modifier une note existante
@Service
public class UpdateNoteHandler {
    private final INoteRepository noteRepository;

    public UpdateNoteHandler(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public UpdateNoteOutput handle(UpdateNoteInput input) {
        // récupérer la note à partir de son id + vérifier qu'elle existe
        NoteRepository note = noteRepository.findById(input.id()).orElse(null);

        if (note == null) {
            return new UpdateNoteOutput(false, "Note non trouvée");
        }

        // A FAIRE : vérifier les permissions de l'utilisateur ?

        // mettre à jour les champs principaux
        note.setName(input.name());
        note.setContent_markdown(input.contentMarkdown());

        // générer le html depuis le markdown
        // (on copie juste tel quel pour l'instant)
        String html = input.contentMarkdown();
        note.setContent_html(html);

        // sauvegarder en base
        noteRepository.save(note);

        // retourner le résultat
        return new UpdateNoteOutput(true, "Note modifiée avec succès.");
    }
}
