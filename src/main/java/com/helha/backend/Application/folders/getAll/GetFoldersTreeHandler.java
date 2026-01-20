package com.helha.backend.Application.folders.getAll;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetFoldersTreeHandler {

    private final IFolderRepository folderRepository;
    private final INoteRepository noteRepository;

    public GetFoldersTreeHandler(IFolderRepository folderRepository, INoteRepository noteRepository) {
        this.folderRepository = folderRepository;
        this.noteRepository = noteRepository;
    }

    public GetFoldersTreeOutput handle(GetFoldersTreeInput input) {

        GetFoldersTreeOutput output = new GetFoldersTreeOutput();

        // 1. Charger tous les dossiers
        List<DbFolder> folders = folderRepository.findAll();

        // 2. Charger toutes les notes
        List<NoteRepository> notes = noteRepository.findAll();

        // 3. Créer une map id → DTO
        Map<Long, GetFoldersTreeOutput.Folder> map = new HashMap<>();

        for (DbFolder f : folders) {
            GetFoldersTreeOutput.Folder dto = new GetFoldersTreeOutput.Folder();
            dto.id = f.getId();
            dto.name = f.getName();
            dto.parentId = (f.getParent() != null ? f.getParent().getId() : null);
            map.put(dto.id, dto);
        }

        // 4. Associer les notes à leur dossier
        for (NoteRepository n : notes) {
            GetFoldersTreeOutput.Note noteDto = new GetFoldersTreeOutput.Note();
            noteDto.id = n.getId();
            noteDto.title = n.getName();

            if (n.getFolder() == null) {
                // note à la racine
                output.rootNotes.add(noteDto);
            } else {
                map.get(n.getFolder().getId()).notes.add(noteDto);
            }
        }

        // 5. Construire l’arborescence
        for (GetFoldersTreeOutput.Folder folder : map.values()) {
            if (folder.parentId == null) {
                output.folders.add(folder);
            } else {
                map.get(folder.parentId).children.add(folder);
            }
        }

        return output;
    }
}