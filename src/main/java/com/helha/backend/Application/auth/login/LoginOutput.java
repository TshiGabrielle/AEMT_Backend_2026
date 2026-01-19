package com.helha.backend.Application.auth.login;

// résultat du login
public record LoginOutput(
        boolean success,
        String message,
        Long userId
) {}