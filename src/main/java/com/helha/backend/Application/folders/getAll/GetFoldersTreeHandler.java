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

        // 1. Charger uniquement les dossiers de l'utilisateur
        List<DbFolder> folders =
                folderRepository.findByUserId(input.userId());

        // 2. Charger uniquement les notes de l'utilisateur
        List<NoteRepository> notes =
                noteRepository.findByUserId(input.userId());

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
                output.rootNotes.add(noteDto);
            } else {
                GetFoldersTreeOutput.Folder folderDto =
                        map.get(n.getFolder().getId());

                if (folderDto != null) {
                    folderDto.notes.add(noteDto);
                }
            }
        }

        // 5. Construire l’arborescence
        for (GetFoldersTreeOutput.Folder folder : map.values()) {
            if (folder.parentId == null) {
                output.folders.add(folder);
            } else {
                GetFoldersTreeOutput.Folder parent = map.get(folder.parentId);
                if (parent != null) {
                    parent.children.add(folder);
                }
            }
        }

        return output;
    }
}