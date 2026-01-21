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

        // On essaie de charger le dossier auquel appartient la note,
        // mais seulement si ce dossier appartient bien à l'utilisateur.
        // Si l'id du dossier n'est pas donné, ou si le dossier ne lui appartient pas,
        // alors 'folder' restera null.
        DbFolder folder = null;
        if (input.idFolder() != null) {
            folder = folderRepository
                    .findByIdAndUserId(input.idFolder(), input.userId())
                    .orElse(null);
        }

        // On crée une nouvelle entité Note (objet qui sera enregistré en base)
        NoteRepository note = new NoteRepository();
        note.setName(input.name());                    // titre de la note
        note.setContent_markdown(input.content_markdown());  // contenu brut en markdown
        note.setContent_html("");                      // le HTML sera généré plus tard
        note.setFolder(folder);                        // on associe la note au dossier (ou null)

        // On associe aussi la note à l'utilisateur qui la crée
        note.setUserId(input.userId());

        // On enregistre la date actuelle pour la création et la dernière mise à jour
        LocalDateTime now = LocalDateTime.now();
        note.setCreated_at(now);
        note.setUpdated_at(now);

        // On calcule les métadonnées demandées :

        String content = input.content_markdown();
        note.setNbcaract(content.length());
        note.setNblines(content.isEmpty() ? 0 : content.split("\n").length);
        note.setNbmots(content.isEmpty() ? 0 : content.split("\\s+").length);
        note.setTaille_octet(content.getBytes().length);

        // On sauvegarde la note en base de données
        NoteRepository savedNote = noteRepository.save(note);

        // On renvoie uniquement les infos nécessaires à la réponse
        return new CreateNoteOutput(
                savedNote.getId(),
                savedNote.getName(),
                savedNote.getCreated_at()
        );
    }
}
