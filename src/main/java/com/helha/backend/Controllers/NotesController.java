package com.helha.backend.Controllers;

import com.helha.backend.Application.notes.create.CreateNoteHandler;
import com.helha.backend.Application.notes.create.CreateNoteInput;
import com.helha.backend.Application.notes.create.CreateNoteOutput;
import com.helha.backend.Application.notes.delete.DeleteNoteHandler;
import com.helha.backend.Application.notes.delete.DeleteNoteOutput;
import com.helha.backend.Application.notes.delete.DeleteNoteInput;



import com.helha.backend.Application.notes.delete.DeleteNoteOutput;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
@CrossOrigin // pour React (Vite)

public class NotesController {

    private final CreateNoteHandler createNoteHandler;
    private final DeleteNoteHandler deleteNoteHandler;


    public NotesController(
            CreateNoteHandler createNoteHandler,
            DeleteNoteHandler deleteNoteHandler
    ) {
        this.createNoteHandler = createNoteHandler;
        this.deleteNoteHandler = deleteNoteHandler;

    }

    // ---------------- CREATE NOTE ----------------
    @PostMapping
    public CreateNoteOutput create(@RequestBody CreateNoteInput input) {
        return createNoteHandler.handle(input);
    }

    // ---------------- DELETE NOTE ----------------
    @DeleteMapping("/{id}")
    public DeleteNoteOutput delete(@PathVariable long id) {
        return deleteNoteHandler.handle(new DeleteNoteInput(id));
    }


}
