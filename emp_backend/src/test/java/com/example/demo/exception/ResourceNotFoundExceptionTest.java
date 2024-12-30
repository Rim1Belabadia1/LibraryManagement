package com.example.demo.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.HttpStatus;

class ResourceNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        // Message attendu de l'exception
        String message = "Resource not found!";

        // Lancer l'exception avec un message
        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        // Vérifier que le message de l'exception est correct
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testExceptionStatusCode() {
        // Message attendu de l'exception
        String message = "Resource not found!";

        // Lancer l'exception avec un message
        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        // Vérifier que le statut de l'exception est NOT_FOUND (404)
        assertEquals(HttpStatus.NOT_FOUND, HttpStatus.valueOf(404));
    }
}
