package com.helha.backend.Application.folders.delete;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import com.helha.backend.Infrastructure.note.INoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//logique métier pour supprimer un dossier
@Service
public class DeleteFolderHandler {
    private IFolderRepository folderRepository;
    private INoteRepository noteRepository;

    public DeleteFolderHandler(IFolderRepository folderRepository, INoteRepository noteRepository) {
        this.folderRepository = folderRepository;
        this.noteRepository = noteRepository;
    }

    @Transactional
    public DeleteFolderOutput handle(DeleteFolderInput input) {

        // récupérer le dossier uniquement s'il appartient à l'utilisateur
        DbFolder folder = folderRepository
                .findByIdAndUserId(input.id(), input.userId())
                .orElse(null);

        if (folder == null) {
            return new DeleteFolderOutput(false, "Dossier introuvable.");
        }

        // Supprimer d'abord toutes les notes du dossier et de ses sous-dossiers
        deleteNotesRecursively(folder);

        // suppression en db (cascade sur sous-dossiers)
        folderRepository.delete(folder);

        return new DeleteFolderOutput(true, "Dossier supprimé avec succès.");
    }

    private void deleteNotesRecursively(DbFolder folder) {
        // supprimer les notes directement liées à ce dossier
        noteRepository.deleteByFolder_Id(folder.getId());

        // supprimer les notes des sous-dossiers récursivement
        if (folder.getChildren() != null) {
            for (DbFolder child : folder.getChildren()) {
                deleteNotesRecursively(child);
            }
        }
    }
}