package com.helha.backend.Application.folders.rename;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import org.springframework.stereotype.Service;

// logique métier pour renommer un dossier
@Service
public class RenameFolderHandler {
    private final IFolderRepository folderRepository;

    public RenameFolderHandler(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public RenameFolderOutput handle(RenameFolderInput input) {
        // récupérer le dossier à partir de son id
        DbFolder folder = folderRepository.findById(input.id()).orElse(null);

        // s'il  n'existe pas, on renvoie une erreur
        if (folder == null) {
            return new RenameFolderOutput(false, "Dossier introuvable");
        }

        // on met à jour le nom
        folder.setName(input.name());

        // on sauvegarde en db
        folderRepository.save(folder);

        return new RenameFolderOutput(true, "Dossier renommé avec succès.");
    }
}
