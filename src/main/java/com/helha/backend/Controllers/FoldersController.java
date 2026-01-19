package com.helha.backend.Controllers;

import com.helha.backend.Application.folders.create.CreateFolderHandler;
import com.helha.backend.Application.folders.create.CreateFolderInput;
import com.helha.backend.Application.folders.create.CreateFolderOutput;
import com.helha.backend.Application.folders.getAll.GetFoldersTreeHandler;
import com.helha.backend.Application.folders.getAll.GetFoldersTreeInput;
import com.helha.backend.Application.folders.getAll.GetFoldersTreeOutput;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/folders")
public class FoldersController {
    // handlers contenant la logique métier
    private final CreateFolderHandler createFolderHandler;
    private final GetFoldersTreeHandler getFoldersTreeHandler;

    public FoldersController(
            CreateFolderHandler createFolderHandler,
            GetFoldersTreeHandler getFoldersTreeHandler
    ) {
        this.createFolderHandler = createFolderHandler;
        this.getFoldersTreeHandler = getFoldersTreeHandler;
    }

    // POST /api/folders
    // crée un nv dossier (éventuellement avec un parent)
    @PostMapping
    public CreateFolderOutput createFolder(@RequestBody CreateFolderInput input) {
        return createFolderHandler.handle(input);
    }

    // GET /api/folders
    // récupère tous les dossiers
    @GetMapping
    public GetFoldersTreeOutput getAll() {
        GetFoldersTreeInput input = new GetFoldersTreeInput();

        // on délègue la logique au handler
        return getFoldersTreeHandler.handle(input);
    }
}