package com.helha.backend.Controllers;

import com.helha.backend.Application.notes.exportPdf.ExportPdfNoteHandler;
import com.helha.backend.Application.notes.exportPdf.ExportPdfNoteInput;
import com.helha.backend.Application.notes.exportPdf.ExportPdfNoteOutput;
import com.helha.backend.Application.notes.exportZip.ExportZipNoteHandler;
import com.helha.backend.Application.notes.exportZip.ExportZipNoteInput;
import com.helha.backend.Application.notes.exportZip.ExportZipNoteOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
public class NotesExportController {

    private final ExportPdfNoteHandler exportPdfNoteHandler;
    private final ExportZipNoteHandler exportZipNoteHandler;

    // injection des handlers via le constructeur
    public NotesExportController(
            ExportPdfNoteHandler exportPdfNoteHandler,
            ExportZipNoteHandler exportZipNoteHandler
    ) {
        this.exportPdfNoteHandler = exportPdfNoteHandler;
        this.exportZipNoteHandler = exportZipNoteHandler;
    }

    // GET /api/notes/{id}/export/pdf
    // exporte une note en pdf
    @GetMapping("/{id}/export/pdf")
    public ExportPdfNoteOutput exportPdf(@PathVariable long id) {
        // On construit l'input comme dans tes autres contrôleurs
        ExportPdfNoteInput input = new ExportPdfNoteInput(id);

        // On délègue toute la logique au handler
        return exportPdfNoteHandler.handle(input);
    }

    // GET /api/notes/{id}/export/zip
    // export une note en zip
    @GetMapping("/{id}/export/zip")
    public ExportZipNoteOutput exportZip(@PathVariable long id) {
        ExportZipNoteInput input = new ExportZipNoteInput(id);

        return exportZipNoteHandler.handle(input);
    }
}
