package com.helha.backend.Application.folders.create;

// données nécessaires pour créer un dossier
public record CreateFolderInput(
        String name,    // nom du dossier
        long userId,    // id de l'utilisateur propriétaire
        Long parentId   // dossier parent (null = racine)
) {}
