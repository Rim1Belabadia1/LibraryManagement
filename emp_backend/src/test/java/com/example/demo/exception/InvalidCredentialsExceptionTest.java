package com.example.demo.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidCredentialsExceptionTest {

    @Test
    void testExceptionMessage() {
        String message = "Invalid credentials provided!";

        // Lancer l'exception avec un message
        InvalidCredentialsException exception = new InvalidCredentialsException(message);

        // Vérifier que le message de l'exception est correct
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testExceptionWithCause() {
        String message = "Invalid credentials provided!";
        Throwable cause = new Throwable("Cause of the issue");

        // Lancer l'exception avec un message et une cause
        InvalidCredentialsException exception = new InvalidCredentialsException(message, cause);

        // Vérifier que le message de l'exception est correct
        assertEquals(message, exception.getMessage());

        // Vérifier que la cause de l'exception est correcte
        assertEquals(cause, exception.getCause());
    }
}
