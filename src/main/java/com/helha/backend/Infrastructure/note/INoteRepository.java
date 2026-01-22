package com.helha.backend.Infrastructure.note;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INoteRepository extends CrudRepository<NoteRepository, Long> {
    // Récupère toutes les notes
    List<NoteRepository> findAll();
    
    // Récupère toutes les notes d'un dossier donné
    List<NoteRepository> findByFolder_Id(Long folderId);

    // Supprime toutes les notes d'un dossier donné
    void deleteByFolder_Id(Long folderId);

    // récupère toutes les notes appartenant à un utilisateur
    // permet d'éviter qu'un user voie les notes des autres
    List<NoteRepository> findByUserId(long userId);

    // récupère toutes les notes d'un dossier
    // (uniquement celles qui appartiennent à l'utilisateur donné)
    List<NoteRepository> findByUserIdAndFolderId(long userId, long folderId);

    // récupère une note par son id (uniquement si elle appartient à l'utilisateur)
    Optional<NoteRepository> findByIdAndUserId(long id, long userId);
}
