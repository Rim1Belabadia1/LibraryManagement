package com.example.demo.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        webRequest = mock(WebRequest.class); // Création d'un mock de WebRequest
    }

    @Test
    void testHandleUserNotFoundException() {
        // Préparer l'exception et la requête simulée
        UserNotFoundException exception = new UserNotFoundException("Utilisateur non trouvé");
        when(webRequest.getDescription(false)).thenReturn("Description de la requête");

        // Appel de la méthode handler pour UserNotFoundException
        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleUserNotFoundException(exception, webRequest);

        // Vérification des résultats
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Utilisateur non trouvé", response.getBody().getMessage());
        assertEquals("Description de la requête", response.getBody().getDetails());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatusCode());
    }

    @Test
    void testHandleInvalidCredentialsException() {
        // Préparer l'exception et la requête simulée
        InvalidCredentialsException exception = new InvalidCredentialsException("Identifiants invalides");
        when(webRequest.getDescription(false)).thenReturn("Description de la requête");

        // Appel de la méthode handler pour InvalidCredentialsException
        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleInvalidCredentialsException(exception, webRequest);

        // Vérification des résultats
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Identifiants invalides", response.getBody().getMessage());
        assertEquals("Description de la requête", response.getBody().getDetails());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getBody().getStatusCode());
    }

    @Test
    void testHandleGlobalException() {
        // Préparer l'exception et la requête simulée
        Exception exception = new Exception("Erreur interne");
        when(webRequest.getDescription(false)).thenReturn("Description de la requête");

        // Appel de la méthode handler pour les exceptions génériques
        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleGlobalException(exception, webRequest);

        // Vérification des résultats
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Une erreur interne est survenue", response.getBody().getMessage());
        assertEquals("Description de la requête", response.getBody().getDetails());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getStatusCode());
    }
}
