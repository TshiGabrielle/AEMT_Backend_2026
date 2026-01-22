package com.helha.backend.Application.notes.exportZip;

import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// handler responsable de créer une archive zip
@Service
public class ExportZipNoteHandler {
    private final INoteRepository noteRepository;

    public ExportZipNoteHandler(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public ExportZipNoteOutput handle(ExportZipNoteInput input) {
        // récupérer la note à exporter
        NoteRepository note = noteRepository.findById(input.id()).orElse(null);
        if (note == null) {
            return null;
        }

        try {
            // préparer un flux pour le zip
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(baos);

            // ajoutet le markdown à l'archive
            zip.putNextEntry(new ZipEntry("note.md"));
            zip.write(note.getContent_markdown().getBytes());
            zip.closeEntry();

            // finaliser le zip
            zip.close();

            // retourner le zip + nom du fichier
            return new ExportZipNoteOutput(
                    baos.toByteArray(),
                    "note_" + input.id() + ".zip"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
