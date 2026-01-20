package com.helha.backend.Application.folders.rename;

// données nécessaires pour renommer le dossier
public record RenameFolderInput(
        long id,        // id du dossier à renommer
        long userId,    // id de l'utilisateur
        String name    // nv nom
) {}
