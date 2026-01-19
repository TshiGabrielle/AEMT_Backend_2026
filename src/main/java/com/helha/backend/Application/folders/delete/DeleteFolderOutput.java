package com.helha.backend.Application.folders.delete;

// résultat après suppression
public record DeleteFolderOutput(
     boolean success,
     String message
) {}
