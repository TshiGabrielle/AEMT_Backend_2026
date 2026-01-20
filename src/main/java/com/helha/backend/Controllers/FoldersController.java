package com.helha.backend.Controllers;

import com.helha.backend.Application.folders.create.CreateFolderHandler;
import com.helha.backend.Application.folders.create.CreateFolderInput;
import com.helha.backend.Application.folders.create.CreateFolderOutput;
import com.helha.backend.Application.folders.delete.DeleteFolderHandler;
import com.helha.backend.Application.folders.delete.DeleteFolderInput;
import com.helha.backend.Application.folders.delete.DeleteFolderOutput;
import com.helha.backend.Application.folders.get.GetFolderNotesHandler;
import com.helha.backend.Application.folders.get.GetFolderNotesInput;
import com.helha.backend.Application.folders.get.GetFolderNotesOutput;
import com.helha.backend.Application.folders.getAll.GetFoldersTreeHandler;
import com.helha.backend.Application.folders.getAll.GetFoldersTreeInput;
import com.helha.backend.Application.folders.getAll.GetFoldersTreeOutput;
import com.helha.backend.Application.folders.rename.RenameFolderHandler;
import com.helha.backend.Application.folders.rename.RenameFolderInput;
import com.helha.backend.Application.folders.rename.RenameFolderOutput;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/folders")
@CrossOrigin(origins = "http://localhost:5173")
public class FoldersController {
    // handlers contenant la logique métier
    private final CreateFolderHandler createFolderHandler;
    private final GetFoldersTreeHandler getFoldersTreeHandler;
    private final RenameFolderHandler renameFolderHandler;
    private final DeleteFolderHandler deleteFolderHandler;
    private final GetFolderNotesHandler getFolderNotesHandler;

    public FoldersController(
            CreateFolderHandler createFolderHandler,
            GetFoldersTreeHandler getFoldersTreeHandler,
            RenameFolderHandler renameFolderHandler,
            DeleteFolderHandler deleteFolderHandler,
            GetFolderNotesHandler getFolderNotesHandler) {
        this.createFolderHandler = createFolderHandler;
        this.getFoldersTreeHandler = getFoldersTreeHandler;
        this.renameFolderHandler = renameFolderHandler;
        this.deleteFolderHandler = deleteFolderHandler;
        this.getFolderNotesHandler = getFolderNotesHandler;
    }

    // POST /api/folders
    // crée un nv dossier (éventuellement avec un parent)
    @PostMapping
    public CreateFolderOutput createFolder(@RequestBody CreateFolderInput input) {
        return createFolderHandler.handle(input);
    }

    // GET /api/folders
    // récupère tous les dossiers de l'utilisateur
    @GetMapping
    public GetFoldersTreeOutput getAll(@RequestParam long userId) {
        GetFoldersTreeInput input = new GetFoldersTreeInput(userId);

        // on délègue la logique au handler
        return getFoldersTreeHandler.handle(input);
    }

    // PUT /api/folders/{id}
    // modifie le nom d'un dossier
    @PutMapping("/{id}")
    public RenameFolderOutput rename(
            @PathVariable long id,
            @RequestParam long userId,
            @RequestBody RenameFolderInput body
    ) {
        // on reconstruit un input
        RenameFolderInput input = new RenameFolderInput(id, userId, body.name());
        return renameFolderHandler.handle(input);
    }

    // DELETE /api/folders/{id}
    // supprime un dossier (+ sous-dossiers et notes)
    @DeleteMapping("/{id}")
    public DeleteFolderOutput delete(
            @PathVariable long id,
            @RequestParam long userId
    ) {
        DeleteFolderInput input = new DeleteFolderInput(id, userId);
        return deleteFolderHandler.handle(input);
    }

    // GET /api/folders/{id}/notes
    // récupère toutes les notes d'un dossier pour un utilisateur
    @GetMapping("/{id}/notes")
    public GetFolderNotesOutput getNotes(
            @PathVariable long id,
            @RequestParam long userId
    ) {
        GetFolderNotesInput input = new GetFolderNotesInput(id, userId);
        return getFolderNotesHandler.handle(input);
    }
}