package com.helha.backend.Application.notes.update;

// données renvoyées après la modif d'une note
public class UpdateNoteOutput {
    public long id;
    public String title;
    public String contentHTML;

    public UpdateNoteOutput(long id, String title, String contentHTML) {
        this.id = id;
        this.title = title;
        this.contentHTML = contentHTML;
    }
}
