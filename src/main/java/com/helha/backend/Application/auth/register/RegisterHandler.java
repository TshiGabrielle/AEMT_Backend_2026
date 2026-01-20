package com.helha.backend.Application.auth.register;

import com.helha.backend.Infrastructure.user.DbUser;
import com.helha.backend.Infrastructure.user.IUserRepository;
import org.springframework.stereotype.Service;

/**
 * Contient la logique métier pour l'inscription.
 */
@Service
public class RegisterHandler {

    private final IUserRepository userRepository;

    public RegisterHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegisterOutput handle(RegisterInput input) {

        // Vérifier si le pseudo existe déjà
        if (userRepository.existsByPseudo(input.pseudo())) {
            return new RegisterOutput(
                    false,
                    "Ce pseudo est déjà utilisé.",
                    null
            );
        }

        // Créer l'utilisateur (sans hashage pour l'instant)
        DbUser user = new DbUser(
                input.pseudo(),
                input.password()
        );

        // Sauvegarde en base
        userRepository.save(user);

        return new RegisterOutput(
                true,
                "Compte créé avec succès.",
                user.getId()
        );
    }
}
