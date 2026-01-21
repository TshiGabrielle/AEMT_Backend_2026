package com.helha.backend.Application.notes.exportPdf;

import com.helha.backend.Infrastructure.note.INoteRepository;
import com.helha.backend.Infrastructure.note.NoteRepository;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.vladsch.flexmark.html.HtmlRenderer;
import org.springframework.stereotype.Service;

import com.vladsch.flexmark.parser.Parser;
import java.io.ByteArrayOutputStream;

@Service
public class ExportPdfNoteHandler {

    private final INoteRepository noteRepository;

    public ExportPdfNoteHandler(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public ExportPdfNoteOutput handle(ExportPdfNoteInput input) {
        // récupérer la note à exporter via l'id
        NoteRepository note = noteRepository.findById(input.id()).orElse(null);
        if (note == null) {
            return null;
        }

        // récupérer le markdown
        String markdown = note.getContent_markdown();

        // convertir le markdown en html (via flexmark)
        Parser parser =  Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html = renderer.render(parser.parse(markdown));

        try {
            // générer le pdf
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);    // html interprété
            builder.toStream(out);                                  // pdf écrit dans un flux
            builder.run();

            // retourner le pdf + nom de fichier
            return new ExportPdfNoteOutput(
                    out.toByteArray(),
                    "note_" + input.id() + ".pdf"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
