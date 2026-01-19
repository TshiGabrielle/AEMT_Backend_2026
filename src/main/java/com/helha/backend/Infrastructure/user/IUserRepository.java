package com.helha.backend.Infrastructure.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<DbUser, Long> {
    // vérifier si un pseudo  existe déjà
    boolean existsByPseudo(String Pseudo);

    // récupérer un utilisateur à partir de son pseudo
    Optional<DbUser> findByPseudo(String Pseudo);
}
