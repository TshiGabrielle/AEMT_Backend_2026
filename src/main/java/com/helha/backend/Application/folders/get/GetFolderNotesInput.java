package com.helha.backend.Application.folders.get;

// données nécessaires pour récupérer les notes d’un dossier
public record GetFolderNotesInput(
        long folderId   // id du dossier
) {}