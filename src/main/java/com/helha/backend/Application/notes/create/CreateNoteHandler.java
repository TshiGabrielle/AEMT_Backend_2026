package com.helha.backend.Application.notes.create;

import com.helha.backend.Infrastructure.folder.IFolderRepository;
import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateNoteHandler {

    private final INoteRepository noteRepository;
    private final IFolderRepository folderRepository;

    public CreateNoteHandler(INoteRepository noteRepository, IFolderRepository folderRepository) {
        this.noteRepository = noteRepository;
        this.folderRepository = folderRepository;
    }

    public CreateNoteOutput handle(CreateNoteInput input) {

        // charger le dossier uniquement s'il appartient à l'utilisateur
        DbFolder folder = null;
        if (input.idFolder() != null) {
            folder = folderRepository
                    .findByIdAndUserId(input.idFolder(), input.userId())
                    .orElse(null);
        }

        // Création de l'entité JPA
        NoteRepository note = new NoteRepository();
        note.setName(input.name());
        note.setContent_markdown(input.content_markdown());
        note.setContent_html(""); // rendu markdown -> html plus tard
        note.setFolder(folder);

        // associer la note à son propriétaire
        note.setUserId(input.userId());

        LocalDateTime now = LocalDateTime.now();
        note.setCreated_at(now);
        note.setUpdated_at(now);

        // Calcul des métadonnées
        String content = input.content_markdown();
        note.setNbcaract(content.length());
        note.setNblines(content.isEmpty() ? 0 : content.split("\n").length);
        note.setNbmots(content.isEmpty() ? 0 : content.split("\\s+").length);
        note.setTaille_octet(content.getBytes().length);

        // Sauvegarde
        NoteRepository savedNote = noteRepository.save(note);

        // Retour Output
        return new CreateNoteOutput(
                savedNote.getId(),
                savedNote.getName(),
                savedNote.getCreated_at()
        );
    }
}