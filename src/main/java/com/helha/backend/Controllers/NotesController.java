package com.helha.backend.Controllers;

import com.helha.backend.Application.notes.update.UpdateNoteHandler;
import com.helha.backend.Application.notes.update.UpdateNoteInput;
import com.helha.backend.Application.notes.update.UpdateNoteOutput;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NotesController {
    private final UpdateNoteHandler updateNoteHandler;

    public NotesController(UpdateNoteHandler updateNoteHandler) {
        this.updateNoteHandler = updateNoteHandler;
    }

    // modifier une note
    // PUT /api/notes/{id}
    @PutMapping("/{id}")
    public UpdateNoteOutput updateNote(
            @PathVariable long id,
            @RequestBody UpdateNoteRequest body
    ) {
        UpdateNoteInput input = new UpdateNoteInput(
                id,
                body.name,
                body.contentMarkdown
        );

        return updateNoteHandler.handle(input);
    }

    // DTO uniquement pour lire le JSON du body
    public static class UpdateNoteRequest {
        public String name;
        public String contentMarkdown;
    }
}
