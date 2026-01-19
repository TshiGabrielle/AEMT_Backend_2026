package com.helha.backend.Controllers;

import com.helha.backend.Application.folders.create.CreateFolderHandler;
import com.helha.backend.Application.folders.create.CreateFolderInput;
import com.helha.backend.Application.folders.create.CreateFolderOutput;
import com.helha.backend.Application.folders.getAll.GetFoldersTreeHandler;
import com.helha.backend.Application.folders.getAll.GetFoldersTreeInput;
import com.helha.backend.Application.folders.getAll.GetFoldersTreeOutput;
import com.helha.backend.Application.folders.rename.RenameFolderHandler;
import com.helha.backend.Application.folders.rename.RenameFolderInput;
import com.helha.backend.Application.folders.rename.RenameFolderOutput;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/folders")
public class FoldersController {
    // handlers contenant la logique métier
    private final CreateFolderHandler createFolderHandler;
    private final GetFoldersTreeHandler getFoldersTreeHandler;
    private final RenameFolderHandler renameFolderHandler;

    public FoldersController(
            CreateFolderHandler createFolderHandler,
            GetFoldersTreeHandler getFoldersTreeHandler,
            RenameFolderHandler renameFolderHandler
    ) {
        this.createFolderHandler = createFolderHandler;
        this.getFoldersTreeHandler = getFoldersTreeHandler;
        this.renameFolderHandler = renameFolderHandler;
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

    // PUT /api/folders/{id}
    // modifie le nom d'un dossier
    @PutMapping("/{id}")
    public RenameFolderOutput rename(
            @PathVariable long id,
            @RequestBody RenameFolderInput body
    ) {
        // on reconstruit un input
        RenameFolderInput input = new RenameFolderInput(id, body.name());
        return renameFolderHandler.handle(input);
    }
}