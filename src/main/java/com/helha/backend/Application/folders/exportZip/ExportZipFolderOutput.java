package com.helha.backend.Application.folders.exportZip;

public record ExportZipFolderOutput(
        byte[] zipBytes,    // contenu du zip
        String folderName   // nom du dossier à télécharger
){}