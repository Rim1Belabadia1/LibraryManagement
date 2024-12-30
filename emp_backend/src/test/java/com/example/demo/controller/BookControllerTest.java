package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    private Book book;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialisation des mocks
        book = new Book(1L, "Book Title", "Book Type", "Book Description", "Book Publisher", "Book Author", "2023-01-01", true, new byte[]{});
    }

    @Test
    public void testGetAllBooks() {
        // Arrange
        List<Book> books = List.of(book);
        when(bookRepository.findAll()).thenReturn(books);

        // Act
        List<Book> result = bookController.getAllBooks();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(book.getName(), result.get(0).getName());
    }

    @Test
    public void testGetBookByIdFound() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Act
        ResponseEntity<Book> response = bookController.getBookById(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(book.getName(), response.getBody().getName());
    }

    @Test
    public void testGetBookByIdNotFound() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Book> response = bookController.getBookById(1L);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateBook() {
        // Arrange
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Act
        Book createdBook = bookController.createBook(book);

        // Assert
        assertNotNull(createdBook);
        assertEquals(book.getName(), createdBook.getName());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testUpdateBookFound() {
        // Arrange
        Book updatedBookDetails = new Book(1L, "Updated Title", "Updated Type", "Updated Description", "Updated Publisher", "Updated Author", "2024-01-01", false, new byte[]{});
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBookDetails);

        // Act
        ResponseEntity<Book> response = bookController.updateBook(1L, updatedBookDetails);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated Title", response.getBody().getName());
        verify(bookRepository, times(1)).save(updatedBookDetails);
    }

    @Test
    public void testUpdateBookNotFound() {
        // Arrange
        Book updatedBookDetails = new Book(1L, "Updated Title", "Updated Type", "Updated Description", "Updated Publisher", "Updated Author", "2024-01-01", false, new byte[]{});
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Book> response = bookController.updateBook(1L, updatedBookDetails);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        verify(bookRepository, times(0)).save(updatedBookDetails);
    }

    @Test
    public void testDeleteBookFound() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Act
        ResponseEntity<Void> response = bookController.deleteBook(1L);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    public void testDeleteBookNotFound() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = bookController.deleteBook(1L);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        verify(bookRepository, times(0)).delete(any(Book.class));
    }
}
