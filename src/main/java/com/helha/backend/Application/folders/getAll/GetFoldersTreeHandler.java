package com.helha.backend.Application.folders.getAll;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import org.springframework.stereotype.Service;

@Service
public class GetFoldersTreeHandler {
    private final IFolderRepository folderRepository;

    public GetFoldersTreeHandler(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public GetFoldersTreeOutput handle(GetFoldersTreeInput input) {
        Iterable<DbFolder> entities = folderRepository.findAll();
        GetFoldersTreeOutput output = new GetFoldersTreeOutput();

        // on parcourt tous les dossiers en base
        for (DbFolder entity : entities) {

            // on crée un dto simple pour le front
            GetFoldersTreeOutput.Folder dto = new GetFoldersTreeOutput.Folder();
            dto.id = entity.getId();
            dto.name = entity.getName();

            // si le dossier a un parent, on met son id
            // sinon, on met null (dossier racine)
            if (entity.getParent() != null) {
                dto.parentId = entity.getParent().getId();
            } else {
                dto.parentId = null;
            }

            // on ajoute ce dossier à la liste des output
            output.folders.add(dto);
        }

        return output;
    }
}
