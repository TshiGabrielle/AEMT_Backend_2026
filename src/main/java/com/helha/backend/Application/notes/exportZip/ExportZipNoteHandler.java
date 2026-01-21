package com.helha.backend.Application.notes.exportZip;

import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import com.vladsch.flexmark.html.HtmlRenderer;
import org.springframework.stereotype.Service;

import com.vladsch.flexmark.parser.Parser;
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

            // générer le html à partir du markdown
            Parser parser = Parser.builder().build();
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            String html = renderer.render(parser.parse(note.getContent_markdown()));

            // ajouter le html dans l'archive
            zip.putNextEntry(new ZipEntry("note.html"));
            zip.write(html.getBytes()); zip.closeEntry();

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
