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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
@CrossOrigin // pour React (Vite)

public class NotesController {

    private final CreateNoteHandler createNoteHandler;
    private final DeleteNoteHandler deleteNoteHandler;
    private final GetNoteHandler getNoteHandler;


    public NotesController(
            CreateNoteHandler createNoteHandler,
            DeleteNoteHandler deleteNoteHandler,
            GetNoteHandler getNoteHandler
    ) {
        this.createNoteHandler = createNoteHandler;
        this.deleteNoteHandler = deleteNoteHandler;
        this.getNoteHandler = getNoteHandler;

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


}
