package com.helha.backend.Controllers;

import com.helha.backend.Application.auth.login.LoginHandler;
import com.helha.backend.Application.auth.login.LoginInput;
import com.helha.backend.Application.auth.login.LoginOutput;
import com.helha.backend.Application.auth.register.RegisterHandler;
import com.helha.backend.Application.auth.register.RegisterInput;
import com.helha.backend.Application.auth.register.RegisterOutput;
import org.springframework.web.bind.annotation.*;

// contrôleur pour l'authentification
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterHandler registerHandler;
    private final LoginHandler loginHandler;

    public AuthController(
            RegisterHandler registerHandler,
            LoginHandler loginHandler
    ) {
        this.registerHandler = registerHandler;
        this.loginHandler = loginHandler;
    }

    /**
     * POST /api/auth/register
     * Permet de créer un nouvel utilisateur.
     */
    @PostMapping("/register")
    public RegisterOutput register(@RequestBody RegisterInput input) {
        // On délègue tout au handler
        return registerHandler.handle(input);
    }

    @PostMapping("/login")
    public LoginOutput login(@RequestBody LoginInput input) {
        return loginHandler.handle(input);
    }
}
