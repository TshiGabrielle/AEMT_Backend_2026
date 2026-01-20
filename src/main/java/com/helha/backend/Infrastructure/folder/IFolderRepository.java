package com.helha.backend.Infrastructure.folder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFolderRepository extends CrudRepository<DbFolder, Long> {

    // récupère tous les dossiers d'un utilisateur
    List<DbFolder> findByUserId(long userId);

    // récupère tous les sous-dossiers d'un dossier donné
    List<DbFolder> findByParentId(long parentId);

    List<DbFolder> findAll();
}