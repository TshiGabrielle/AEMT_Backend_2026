package com.helha.backend.Application.notes.update;

// données nécessaires pour modifier une note
public class UpdateNoteInput {
    // id de la note à modifier
    public long id;

    // titre (ou nouveau titre) de la note
    public String title;

    // nouveau contenu en markdown
    public String contentMarkdown;

    public UpdateNoteInput(long id, String title, String contentMarkdown) {
        this.id = id;
        this.title = title;
        this.contentMarkdown = contentMarkdown;
    }
}
