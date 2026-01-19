package com.helha.backend.Application.folders.create;

// résultat de la création d'un dossier
public record CreateFolderOutput(
        boolean success,
        String message
){}
