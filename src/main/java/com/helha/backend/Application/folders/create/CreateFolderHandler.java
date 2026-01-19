package com.helha.backend.Application.folders.create;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import org.springframework.stereotype.Service;

// logique pour créer un dossier
@Service
public class CreateFolderHandler {
    private final IFolderRepository folderRepository;

    public CreateFolderHandler(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public CreateFolderOutput handle(CreateFolderInput input) {
        // créer l'entité dossier
        DbFolder folder = new DbFolder();
        folder.setName(input.name());
        folder.setUserId(input.userId());

        // gérer le parent si présent
        if (input.parentId() != null) {
            DbFolder parent = folderRepository.findById(input.parentId()).orElse(null);

            if (parent == null) {
                return new CreateFolderOutput(false, "Dossier parent introuvable.");
            }

            folder.setParent(parent);
        }

        // sauvegarde en db
        DbFolder saved = folderRepository.save(folder);

        return new CreateFolderOutput(
                true,
                "Dossier créé avec succès."
        );
    }
}
