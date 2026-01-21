package com.helha.backend.Application.notes.exportZip;

// données nécessaires pour exporter une note en pdf
public record ExportZipNoteInput(
        long id // id de la note à exporter
) {}
