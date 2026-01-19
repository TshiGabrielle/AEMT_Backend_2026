package com.helha.backend.Controllers;

import com.helha.backend.Infrastructure.folder.DbFolder;
import com.helha.backend.Infrastructure.folder.IFolderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour gérer les dossiers
 */
@RestController
@RequestMapping("/api/folders")
public class FoldersController {

    private final IFolderRepository folderRepository;

    public FoldersController(IFolderRepository folderRepository) {
        this.folderRepository = folderRepository;
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
        if (userId != null) {
            return ResponseEntity.ok(folderRepository.findByUserId(userId));


        }

        return ResponseEntity.ok((List<DbFolder>) folderRepository.findAll());
    }

    /**
     * Récupérer un dossier par son ID
     * GET /api/folders/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<DbFolder> getFolder(@PathVariable Long id) {
        return folderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Créer un nouveau dossier
     * POST /api/folders
     */
    @PostMapping
    public ResponseEntity<DbFolder> createFolder(@RequestBody DbFolder folder) {
        DbFolder savedFolder = folderRepository.save(folder);
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
        return folderRepository.findById(id)
                .map(folder -> {
                    folder.setName(folderUpdate.getName());
                    DbFolder updated = folderRepository.save(folder);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Supprimer un dossier (+ sous-dossiers et notes)
     * DELETE /api/folders/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long id) {
        if (!folderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        folderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupérer tous les sous-dossiers d'un dossier
     * GET /api/folders/{id}/children
     */
    @GetMapping("/{id}/children")
    public ResponseEntity<List<DbFolder>> getChildren(@PathVariable Long id) {
        return ResponseEntity.ok(folderRepository.findByParentId(id));
    }
}

