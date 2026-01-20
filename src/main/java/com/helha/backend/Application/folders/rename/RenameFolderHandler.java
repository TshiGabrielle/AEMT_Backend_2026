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

        // récupérer le dossier uniquement s'il appartient à l'utilisateur
        DbFolder folder = folderRepository
                .findByIdAndUserId(input.id(), input.userId())
                .orElse(null);

        if (folder == null) {
            return new RenameFolderOutput(false, "Dossier introuvable");
        }

        folder.setName(input.name());
        folderRepository.save(folder);

        return new RenameFolderOutput(true, "Dossier renommé avec succès.");
    }
}