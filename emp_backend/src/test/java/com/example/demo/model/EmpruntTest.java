package com.example.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class EmpruntTest {

    private Emprunt emprunt;

    @BeforeEach
    void setUp() {
        // Initialiser l'objet Emprunt avant chaque test
        emprunt = new Emprunt(1L, "Livre 1", "John Doe", "2024-01-01", "2024-02-01");
    }

    @Test
    void testGettersAndSetters() {
        // Vérification des getters et setters
        assertEquals(1L, emprunt.getId());
        assertEquals("Livre 1", emprunt.getNomLivre());
        assertEquals("John Doe", emprunt.getFullName());
        assertEquals(LocalDate.of(2024, 1, 1), emprunt.getDateAcquisition());
        assertEquals(LocalDate.of(2024, 2, 1), emprunt.getDateRetour());
    }

    @Test
    void testConstructor() {
        // Tester le constructeur avec les paramètres
        LocalDate acquisitionDate = LocalDate.of(2024, 1, 1);
        LocalDate returnDate = LocalDate.of(2024, 2, 1);
        Emprunt empruntFromConstructor = new Emprunt(2L, "Livre 2", "Jane Smith", "2024-01-10", "2024-02-10");

        assertEquals(2L, empruntFromConstructor.getId());
        assertEquals("Livre 2", empruntFromConstructor.getNomLivre());
        assertEquals("Jane Smith", empruntFromConstructor.getFullName());
        assertEquals(acquisitionDate, empruntFromConstructor.getDateAcquisition());
        assertEquals(returnDate, empruntFromConstructor.getDateRetour());
    }

    @Test
    void testEmptyConstructor() {
        // Tester le constructeur vide
        Emprunt emptyEmprunt = new Emprunt();

        assertNull(emptyEmprunt.getNomLivre());
        assertNull(emptyEmprunt.getFullName());
        assertNull(emptyEmprunt.getDateAcquisition());
        assertNull(emptyEmprunt.getDateRetour());
    }

    @Test
    void testSetters() {
        // Tester les setters
        emprunt.setNomLivre("Livre 3");
        emprunt.setFullName("Alice Brown");
        emprunt.setDateAcquisition(LocalDate.of(2024, 3, 1));
        emprunt.setDateRetour(LocalDate.of(2024, 4, 1));

        assertEquals("Livre 3", emprunt.getNomLivre());
        assertEquals("Alice Brown", emprunt.getFullName());
        assertEquals(LocalDate.of(2024, 3, 1), emprunt.getDateAcquisition());
        assertEquals(LocalDate.of(2024, 4, 1), emprunt.getDateRetour());
    }
}
