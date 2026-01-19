package com.helha.backend.Application.auth.register;

// données nécessaires pour créer un compte
public record RegisterInput(
        String pseudo,
        String password
) {}