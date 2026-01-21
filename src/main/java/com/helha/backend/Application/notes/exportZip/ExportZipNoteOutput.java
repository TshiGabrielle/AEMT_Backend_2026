package com.helha.backend.Application.notes.exportZip;

// résultat de l'export zip
public record ExportZipNoteOutput(
        byte[] zipBytes, // contenu du ZIP
        String filename // nom du fichier à télécharger
) {}
