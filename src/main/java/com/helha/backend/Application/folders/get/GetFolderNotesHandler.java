package com.helha.backend.Application.folders.get;

import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import org.springframework.stereotype.Service;

// logique pour récupérer les notes d’un dossier
@Service
public class GetFolderNotesHandler {

    private final INoteRepository noteRepository;

    public GetFolderNotesHandler(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public GetFolderNotesOutput handle(GetFolderNotesInput input) {

        Iterable<NoteRepository> entities =
                noteRepository.findByIdFolder(input.folderId());

        GetFolderNotesOutput output = new GetFolderNotesOutput();

        for (NoteRepository entity : entities) {
            // mapping simple : entity -> DTO
            GetFolderNotesOutput.Note dto =
                    new GetFolderNotesOutput.Note();

            dto.id = entity.getId();
            dto.name = entity.getName();

            output.notes.add(dto);
        }

        return output;
    }
}