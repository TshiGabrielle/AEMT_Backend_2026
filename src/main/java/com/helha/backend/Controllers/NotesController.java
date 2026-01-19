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
import com.helha.backend.Application.notes.list.ListNotesHandler;
import com.helha.backend.Application.notes.list.ListNotesOutput;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:5173")
public class NotesController {

    private final CreateNoteHandler createNoteHandler;
    private final DeleteNoteHandler deleteNoteHandler;
    private final GetNoteHandler getNoteHandler;
    private final UpdateNoteHandler updateNoteHandler;
    private final ListNotesHandler listNotesHandler;


    public NotesController(
            CreateNoteHandler createNoteHandler,
            DeleteNoteHandler deleteNoteHandler,
            GetNoteHandler getNoteHandler,
            UpdateNoteHandler updateNoteHandler,
            ListNotesHandler listNotesHandler
    ) {
        this.createNoteHandler = createNoteHandler;
        this.deleteNoteHandler = deleteNoteHandler;
        this.getNoteHandler = getNoteHandler;
        this.updateNoteHandler = updateNoteHandler;
        this.listNotesHandler = listNotesHandler;

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

    // ---------------- LIST NOTES ----------------
    @GetMapping
    public List<ListNotesOutput> listNotes() {
        return listNotesHandler.handle();
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
