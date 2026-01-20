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

        // récupérer le dossier uniquement s'il appartient à l'utilisateur
        DbFolder folder = folderRepository
                .findByIdAndUserId(input.id(), input.userId())
                .orElse(null);

        if (folder == null) {
            return new DeleteFolderOutput(false, "Dossier introuvable.");
        }

        // suppression en db (cascade sur sous-dossiers et notes)
        folderRepository.delete(folder);

        return new DeleteFolderOutput(true, "Dossier supprimé avec succès.");
    }
}