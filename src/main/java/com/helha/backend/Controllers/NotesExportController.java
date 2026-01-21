package com.helha.backend.Controllers;

import com.helha.backend.Application.notes.exportPdf.ExportPdfNoteHandler;
import com.helha.backend.Application.notes.exportPdf.ExportPdfNoteInput;
import com.helha.backend.Application.notes.exportPdf.ExportPdfNoteOutput;
import com.helha.backend.Application.notes.exportZip.ExportZipNoteHandler;
import com.helha.backend.Application.notes.exportZip.ExportZipNoteInput;
import com.helha.backend.Application.notes.exportZip.ExportZipNoteOutput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

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
    public ResponseEntity<byte[]> exportPdf(@PathVariable long id) {
        ExportPdfNoteOutput output = exportPdfNoteHandler.handle(new  ExportPdfNoteInput(id));

        // si la note n'existe pas, erreur 404
        if (output == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + output.filename())
                .contentType(MediaType.APPLICATION_PDF)
                .body(output.pdfBytes());
    }

    // GET /api/notes/{id}/export/zip
    // export une note en zip
    @GetMapping("/{id}/export/zip")
    public ResponseEntity<byte[]> exportZip(@PathVariable long id) {
        ExportZipNoteOutput output = exportZipNoteHandler.handle(new  ExportZipNoteInput(id));

        // si la note n'existe pas, erreur 404
        if (output == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + output.filename())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(output.zipBytes());
    }
}
