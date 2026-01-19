package com.helha.backend.Controllers;

import com.helha.backend.Application.notes.create.CreateNoteHandler;
import com.helha.backend.Application.notes.create.CreateNoteInput;
import com.helha.backend.Application.notes.create.CreateNoteOutput;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
@CrossOrigin // pour React (Vite)

public class NotesController {

    private final CreateNoteHandler createNoteHandler;


    public NotesController(
            CreateNoteHandler createNoteHandler
    ) {
        this.createNoteHandler = createNoteHandler;

    }

    // ---------------- CREATE NOTE ----------------
    @PostMapping
    public CreateNoteOutput create(@RequestBody CreateNoteInput input) {
        return createNoteHandler.handle(input);
    }


}
