package com.helha.backend.Application.notes.exportPdf;

public record ExportPdfNoteOutput(
        byte[] pdfBytes, String filename
) {}