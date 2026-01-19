package com.helha.backend.Controllers;

import com.helha.backend.Application.folders.create.CreateFolderHandler;
import com.helha.backend.Application.folders.create.CreateFolderInput;
import com.helha.backend.Application.folders.create.CreateFolderOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/folders")
public class FoldersController {
    // handler contenant la logique métier
    private final CreateFolderHandler createFolderHandler;

    public FoldersController(CreateFolderHandler createFolderHandler) {
        this.createFolderHandler = createFolderHandler;
    }

    // POST /api/folders
    // crée un nv dossier (éventuellement avec un parent)
    @PostMapping
    public CreateFolderOutput createFolder(@RequestBody CreateFolderInput input) {
        return createFolderHandler.handle(input);
    }
}