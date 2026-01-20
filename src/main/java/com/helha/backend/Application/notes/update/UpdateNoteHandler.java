package com.helha.backend.Application.notes.update;

import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

// modifier une note existante
@Service
public class UpdateNoteHandler {
    private final INoteRepository noteRepository;

    public UpdateNoteHandler(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public UpdateNoteOutput handle(UpdateNoteInput input) {
        // Récupérer la note uniquement si elle appartient à l'utilisateur
        NoteRepository note =
                noteRepository.findByIdAndUserId(input.id(), input.userId())
                        .orElse(null);

        if (note == null) {
            return new UpdateNoteOutput(false, "Note non trouvée");
        }


        // mettre à jour les champs principaux
        note.setName(input.name());
        note.setContent_markdown(input.contentMarkdown());

        // Mise à jour de la date de modification
        note.setUpdated_at(LocalDateTime.now());

        // Recalcul des métadonnées
        String content = input.contentMarkdown();
        note.setNbcaract(content.length());
        note.setNblines(content.isEmpty() ? 0 : content.split("\n").length);
        note.setNbmots(content.isEmpty() ? 0 : content.split("\\s+").length);
        note.setTaille_octet(content.getBytes().length);


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
