package com.example.demo.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorDetailsTest {

    @Test
    void testConstructorAndGetters() {
        // Création d'une instance de ErrorDetails
        ErrorDetails errorDetails = new ErrorDetails(404, "Not Found", "The requested resource was not found");

        // Vérification que le constructeur initialise correctement les champs
        assertEquals(404, errorDetails.getStatusCode());
        assertEquals("Not Found", errorDetails.getMessage());
        assertEquals("The requested resource was not found", errorDetails.getDetails());
    }

    @Test
    void testSetters() {
        // Création d'une instance de ErrorDetails
        ErrorDetails errorDetails = new ErrorDetails(0, "", "");

        // Mise à jour des valeurs via les setters
        errorDetails.setStatusCode(500);
        errorDetails.setMessage("Internal Server Error");
        errorDetails.setDetails("An unexpected error occurred");

        // Vérification que les setters fonctionnent correctement
        assertEquals(500, errorDetails.getStatusCode());
        assertEquals("Internal Server Error", errorDetails.getMessage());
        assertEquals("An unexpected error occurred", errorDetails.getDetails());
    }

    @Test
    void testGettersAndSettersAfterModification() {
        // Création d'une instance de ErrorDetails avec des valeurs par défaut
        ErrorDetails errorDetails = new ErrorDetails(400, "Bad Request", "Invalid parameters");

        // Modification des valeurs
        errorDetails.setStatusCode(401);
        errorDetails.setMessage("Unauthorized");
        errorDetails.setDetails("Missing authentication token");

        // Vérification que les getters renvoient les bonnes valeurs après modification
        assertEquals(401, errorDetails.getStatusCode());
        assertEquals("Unauthorized", errorDetails.getMessage());
        assertEquals("Missing authentication token", errorDetails.getDetails());
    }
}
