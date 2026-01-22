package com.helha.backend.Controllers;

import com.helha.backend.Application.folders.exportZip.ExportZipFolderHandler;
import com.helha.backend.Application.folders.exportZip.ExportZipFolderInput;
import com.helha.backend.Application.folders.exportZip.ExportZipFolderOutput;
import com.helha.backend.Application.notes.exportPdf.ExportPdfNoteHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/folders")
@CrossOrigin(origins = "http://localhost:5173")
public class FoldersExportController {
    private final ExportZipFolderHandler exportZipFolderHandler;

    public FoldersExportController(ExportZipFolderHandler exportZipFolderHandler) {
        this.exportZipFolderHandler = exportZipFolderHandler;
    }

    @GetMapping("{id}/export/zip")
    public ResponseEntity<byte[]> getZip(@PathVariable long id) {
        ExportZipFolderOutput output = exportZipFolderHandler.handle(new ExportZipFolderInput(id));

        // si le dossier n'existe pas, on renvoie une erreur 404
        if (output == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
        // indique au navigateur de télécharger un fichier
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + output.folderName() + ".zip\"")
        // type de contenu binaire
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
        // contenu du fichier
            .body(output.zipBytes());
    }
}
