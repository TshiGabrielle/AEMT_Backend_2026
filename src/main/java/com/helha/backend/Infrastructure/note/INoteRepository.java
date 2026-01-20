package com.helha.backend.Infrastructure.note;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INoteRepository extends CrudRepository<NoteRepository, Long> {
    // Récupère toutes les notes
    List<NoteRepository> findAll();
    
    // Récupère toutes les notes d'un dossier donné
    List<NoteRepository> findByFolder_Id(Long folderId);
}
