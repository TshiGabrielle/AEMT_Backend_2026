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

            // on récupère le parent uniquement s'il appartient à l'utilisateur
            DbFolder parent = folderRepository
                    .findByIdAndUserId(input.parentId(), input.userId())
                    .orElse(null);

            if (parent == null) {
                return new CreateFolderOutput(false, "Dossier parent introuvable.", null, null, input.parentId());
            }

            folder.setParent(parent);
        }

        // sauvegarde en db
        DbFolder saved = folderRepository.save(folder);

        return new CreateFolderOutput(
                true,
                "Dossier créé avec succès.",
                saved.getId(),
                saved.getName(),
                input.parentId()
        );
    }
}
