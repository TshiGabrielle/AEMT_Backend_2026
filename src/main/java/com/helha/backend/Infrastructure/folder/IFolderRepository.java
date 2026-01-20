package com.helha.backend.Infrastructure.folder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFolderRepository extends CrudRepository<DbFolder, Long> {

    // récupère tous les dossiers d'un utilisateur
    List<DbFolder> findByUserId(long userId);

    // récupère tous les sous-dossiers d'un dossier donné
    List<DbFolder> findByParentId(long parentId);

    // récupère tous les dossiers
    List<DbFolder> findAll();

    // récupère tous les sous-dossiers d'un parent (uniquement pour un user donné)
    List<DbFolder> findByUserIdAndParentId(long userId, Long parentId);

    // récupère les dossiers racines (parent = null) d'un user
    List<DbFolder> findByUserIdAndParentIsNull(long userId);

    // récupère un dossier par id (uniquement s'il appartient à l'utilisateur)
    Optional<DbFolder> findByIdAndUserId(Long id, long userId);
}