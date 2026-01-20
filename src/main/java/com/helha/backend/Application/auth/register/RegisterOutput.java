package com.helha.backend.Application.auth.register;
/**
 * Réponse renvoyée après une tentative d'inscription.
 */
public record RegisterOutput(
        boolean success,
        String message,
        Long userId
) {}
