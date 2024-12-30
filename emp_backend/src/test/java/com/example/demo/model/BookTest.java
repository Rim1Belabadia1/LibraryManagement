package com.example.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        // Initialiser l'objet Book avant chaque test
        book = new Book("Le Livre", "Science Fiction", "Un livre fascinant", "Editeur X", "Auteur Y", new Date(), new byte[]{1, 2, 3}, "Disponible");
    }

    @Test
    void testGettersAndSetters() {
        // Vérification des getters et setters

        assertEquals("Le Livre", book.getName());
        assertEquals("Science Fiction", book.getType());
        assertEquals("Un livre fascinant", book.getDescription());
        assertEquals("Editeur X", book.getEditeur());
        assertEquals("Auteur Y", book.getAuteur());
        assertNotNull(book.getDateDePublication()); // On vérifie qu'une date est bien définie
        assertArrayEquals(new byte[]{1, 2, 3}, book.getPhoto());
        assertEquals("Disponible", book.getAvailability());
    }

    @Test
    void testConstructor() {
        // Tester le constructeur avec les paramètres
        Date publicationDate = new Date();
        Book bookFromConstructor = new Book("Title", "Type", "Description", "Publisher", "Author", publicationDate, new byte[]{1, 2, 3}, "Disponible");

        assertEquals("Title", bookFromConstructor.getName());
        assertEquals("Type", bookFromConstructor.getType());
        assertEquals("Description", bookFromConstructor.getDescription());
        assertEquals("Publisher", bookFromConstructor.getEditeur());
        assertEquals("Author", bookFromConstructor.getAuteur());
        assertEquals(publicationDate, bookFromConstructor.getDateDePublication());
        assertArrayEquals(new byte[]{1, 2, 3}, bookFromConstructor.getPhoto());
        assertEquals("Disponible", bookFromConstructor.getAvailability());
    }

    @Test
    void testEmptyConstructor() {
        // Tester le constructeur vide
        Book emptyBook = new Book();

        assertNull(emptyBook.getName());
        assertNull(emptyBook.getType());
        assertNull(emptyBook.getDescription());
        assertNull(emptyBook.getEditeur());
        assertNull(emptyBook.getAuteur());
        assertNull(emptyBook.getDateDePublication());
        assertNull(emptyBook.getPhoto());
        assertNull(emptyBook.getAvailability());
    }

    @Test
    void testSetters() {
        // Tester les setters
        book.setName("New Title");
        book.setType("Fantasy");
        book.setDescription("New description");
        book.setEditeur("New Publisher");
        book.setAuteur("New Author");
        book.setDateDePublication(new Date());
        book.setPhoto(new byte[]{4, 5, 6});
        book.setAvailability("Indisponible");

        assertEquals("New Title", book.getName());
        assertEquals("Fantasy", book.getType());
        assertEquals("New description", book.getDescription());
        assertEquals("New Publisher", book.getEditeur());
        assertEquals("New Author", book.getAuteur());
        assertNotNull(book.getDateDePublication());
        assertArrayEquals(new byte[]{4, 5, 6}, book.getPhoto());
        assertEquals("Indisponible", book.getAvailability());
    }
}
