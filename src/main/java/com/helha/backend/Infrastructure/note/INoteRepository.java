package com.helha.backend.Infrastructure.note;

import com.helha.backend.Domain.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INoteRepository extends CrudRepository<NoteRepository, Long>{
    //Récupère toutes les notes
    List<NoteRepository> findAll();

    // récupère toutes les notes d'un dossier donné
    List<NoteRepository> findByIdFolder(long idFolder);
}
