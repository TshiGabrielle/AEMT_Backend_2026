package com.helha.backend.Controllers;

import com.helha.backend.Application.folder.*;
import com.helha.backend.Infrastructure.folder.DbFolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour gérer les dossiers
 */
@RestController
@RequestMapping("/api/folders")
public class FoldersController {

    private final ListFolders listFolders;
    private final GetFolder getFolder;
    private final CreateFolder createFolder;
    private final UpdateFolder updateFolder;
    private final DeleteFolder deleteFolder;
    private final GetFolderChildren getFolderChildren;

    public FoldersController(
            ListFolders listFolders,
            GetFolder getFolder,
            CreateFolder createFolder,
            UpdateFolder updateFolder,
            DeleteFolder deleteFolder,
            GetFolderChildren getFolderChildren
    ) {
        this.listFolders = listFolders;
        this.getFolder = getFolder;
        this.createFolder = createFolder;
        this.updateFolder = updateFolder;
        this.deleteFolder = deleteFolder;
        this.getFolderChildren = getFolderChildren;
    }

    /**
     * Lister tous les dossiers ou ceux d'un utilisateur
     * GET /api/folders
     * GET /api/folders?userId={userId}
     */
    @GetMapping
    public ResponseEntity<List<DbFolder>> listFolders(
            @RequestParam(required = false) Long userId
    ) {
        return ResponseEntity.ok(listFolders.execute(userId));
    }

    /**
     * Récupérer un dossier par son ID
     * GET /api/folders/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<DbFolder> getFolder(@PathVariable Long id) {
        return getFolder.execute(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Créer un nouveau dossier
     * POST /api/folders
     */
    @PostMapping
    public ResponseEntity<DbFolder> createFolder(@RequestBody DbFolder folder) {
        DbFolder savedFolder = createFolder.execute(folder);
        return ResponseEntity.status(201).body(savedFolder);
    }

    /**
     * Renommer un dossier
     * PUT /api/folders/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<DbFolder> renameFolder(
            @PathVariable Long id,
            @RequestBody DbFolder folderUpdate
    ) {
        return updateFolder.execute(id, folderUpdate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Supprimer un dossier (+ sous-dossiers et notes)
     * DELETE /api/folders/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long id) {
        if (deleteFolder.execute(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Récupérer tous les sous-dossiers d'un dossier
     * GET /api/folders/{id}/children
     */
    @GetMapping("/{id}/children")
    public ResponseEntity<List<DbFolder>> getChildren(@PathVariable Long id) {
        return ResponseEntity.ok(getFolderChildren.execute(id));
    }
}

