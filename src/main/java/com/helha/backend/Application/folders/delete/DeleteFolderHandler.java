package com.helha.backend.Application.folders.delete;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import org.springframework.stereotype.Service;

//logique métier pour supprimer un dossier
@Service
public class DeleteFolderHandler {
    private IFolderRepository folderRepository;

    public DeleteFolderHandler(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public DeleteFolderOutput handle(DeleteFolderInput input) {
        // vérifier que le dossier existe
        DbFolder folder = folderRepository.findById(input.id()).orElse(null);

        if (folder == null) {
            return new DeleteFolderOutput(false, "Dossier introuvable.");
        }

        // suppression en db
        // grâce aux cascades, les sous-dossiers et les notes sont également supprimés
        folderRepository.delete(folder);

        return new DeleteFolderOutput(true, "Dossier supprimé avec succès.");
    }
}
