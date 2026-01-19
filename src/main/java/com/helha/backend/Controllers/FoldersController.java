package com.helha.backend.Controllers;

import com.helha.backend.Application.folders.create.CreateFolderHandler;
import com.helha.backend.Application.folders.create.CreateFolderInput;
import com.helha.backend.Application.folders.create.CreateFolderOutput;
import com.helha.backend.Application.folders.delete.DeleteFolderHandler;
import com.helha.backend.Application.folders.delete.DeleteFolderInput;
import com.helha.backend.Application.folders.delete.DeleteFolderOutput;
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
    private final DeleteFolderHandler deleteFolderHandler;

    public FoldersController(
            CreateFolderHandler createFolderHandler,
            GetFoldersTreeHandler getFoldersTreeHandler,
            RenameFolderHandler renameFolderHandler,
            DeleteFolderHandler deleteFolderHandler) {
        this.createFolderHandler = createFolderHandler;
        this.getFoldersTreeHandler = getFoldersTreeHandler;
        this.renameFolderHandler = renameFolderHandler;
        this.deleteFolderHandler = deleteFolderHandler;
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

    // DELETE /api/folders/{id}
    // supprime un dossier (+ sous-dossiers et notes)
    @DeleteMapping("/{id}")
    public DeleteFolderOutput delete(@PathVariable long id) {
        DeleteFolderInput input = new DeleteFolderInput(id);
        return deleteFolderHandler.handle(input);
    }
}