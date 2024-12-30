package com.example.demo.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        // Message attendu de l'exception
        String message = "User not found!";

        // Lancer l'exception avec un message
        UserNotFoundException exception = new UserNotFoundException(message);

        // Vérifier que le message de l'exception est correct
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testExceptionWithCause() {
        // Message attendu et cause de l'exception
        String message = "User not found!";
        Throwable cause = new Throwable("Cause of the exception");

        // Lancer l'exception avec un message et une cause
        UserNotFoundException exception = new UserNotFoundException(message, cause);

        // Vérifier que le message de l'exception est correct
        assertEquals(message, exception.getMessage());

        // Vérifier que la cause de l'exception est correcte
        assertEquals(cause, exception.getCause());
    }
}
