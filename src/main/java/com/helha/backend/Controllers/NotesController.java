package com.helha.backend.Controllers;

import com.helha.backend.Application.notes.create.CreateNoteHandler;
import com.helha.backend.Application.notes.create.CreateNoteInput;
import com.helha.backend.Application.notes.create.CreateNoteOutput;
import com.helha.backend.Application.notes.delete.DeleteNoteHandler;
import com.helha.backend.Application.notes.delete.DeleteNoteOutput;
import com.helha.backend.Application.notes.delete.DeleteNoteInput;
import com.helha.backend.Application.notes.get.GetNoteHandler;
import com.helha.backend.Application.notes.get.GetNoteInput;
import com.helha.backend.Application.notes.get.GetNoteOutput;
import com.helha.backend.Application.notes.update.UpdateNoteHandler;
import com.helha.backend.Application.notes.update.UpdateNoteInput;
import com.helha.backend.Application.notes.update.UpdateNoteOutput;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private final CreateNoteHandler createNoteHandler;
    private final DeleteNoteHandler deleteNoteHandler;
    private final GetNoteHandler getNoteHandler;
    private final UpdateNoteHandler updateNoteHandler;


    public NotesController(
            CreateNoteHandler createNoteHandler,
            DeleteNoteHandler deleteNoteHandler,
            GetNoteHandler getNoteHandler,
            UpdateNoteHandler updateNoteHandler
    ) {
        this.createNoteHandler = createNoteHandler;
        this.deleteNoteHandler = deleteNoteHandler;
        this.getNoteHandler = getNoteHandler;
        this.updateNoteHandler = updateNoteHandler;

    }

    // ---------------- CREATE NOTE ----------------
    @PostMapping
    public CreateNoteOutput create(@RequestBody CreateNoteInput input) {
        return createNoteHandler.handle(input);
    }

    // ---------------- GET NOTE BY ID ----------------
    @GetMapping("/{id}")
    public GetNoteOutput get(@PathVariable long id) {
        return getNoteHandler.handle(new GetNoteInput(id));
    }


    // ---------------- DELETE NOTE ----------------
    @DeleteMapping("/{id}")
    public DeleteNoteOutput delete(@PathVariable long id) {
        return deleteNoteHandler.handle(new DeleteNoteInput(id));
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
