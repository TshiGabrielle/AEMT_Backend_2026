package com.helha.backend.Infrastructure.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<DbUser, Long> {
    // vérifier si un pseudo  existe déjà
    boolean existsByPseudo(String Pseudo);
}
