package com.helha.backend.Application.auth.login;

// données envoyées par le client pour se connecter
public record LoginInput(
        String pseudo,
        String password
) {}
