package com.helha.backend.Application.folders.delete;

// données nécessaires pour supprimer un dossier
public record DeleteFolderInput(
        long id     // id du dossier à supprimer
) {}
