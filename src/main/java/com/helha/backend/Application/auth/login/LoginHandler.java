package com.helha.backend.Application.auth.login;

import com.helha.backend.Infrastructure.user.DbUser;
import com.helha.backend.Infrastructure.user.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginHandler {

    private final IUserRepository userRepository;

    public LoginHandler(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginOutput handle(LoginInput input) {
        // chercher l'utilisateur par son pseudo
        DbUser user = userRepository.findByPseudo(input.pseudo()).orElse(null);

        if (user == null) {
            return new LoginOutput(false, "Pseudo inexistant.", null);
        }

        // comparaison simple du mot de passe (pas de hash pour l’instant)
        if (!user.getPassword().equals(input.password())) {
            return new LoginOutput(false, "Mot de passe incorrect.", null);
        }

        // succès
        return new LoginOutput(true, "Connexion réussie.", user.getId());
    }
}
