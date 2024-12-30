package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice // Cette annotation indique que cette classe gère les exceptions globales
public class GlobalExceptionHandler {

    // Gère les exceptions UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        // Crée l'objet ErrorDetails avec les informations de l'exception
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.NOT_FOUND.value(), // Code HTTP 404
                ex.getMessage(),              // Message d'erreur
                request.getDescription(false) // Détails de la requête (uri, etc.)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); // Retourne une réponse avec le statut 404
    }

    // Gère les exceptions InvalidCredentialsException
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleInvalidCredentialsException(InvalidCredentialsException ex, WebRequest request) {
        // Crée l'objet ErrorDetails avec les informations de l'exception
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.UNAUTHORIZED.value(), // Code HTTP 401
                ex.getMessage(),                  // Message d'erreur
                request.getDescription(false)     // Détails de la requête
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED); // Retourne une réponse avec le statut 401
    }

    // Gère les exceptions génériques
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        // Crée l'objet ErrorDetails avec les informations de l'exception générique
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // Code HTTP 500
                "Une erreur interne est survenue",           // Message d'erreur générique
                request.getDescription(false)               // Détails de la requête
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); // Retourne une réponse avec le statut 500
    }
}
