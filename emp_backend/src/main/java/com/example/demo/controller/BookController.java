package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Obtenir tous les livres
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Obtenir un livre par ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ajouter un nouveau livre
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Mettre à jour un livre existant
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setName(bookDetails.getName());
            book.setType(bookDetails.getType());
            book.setDescription(bookDetails.getDescription());
            book.setEditeur(bookDetails.getEditeur());
            book.setAuteur(bookDetails.getAuteur());
            book.setDateDePublication(bookDetails.getDateDePublication());
            book.setAvailability(bookDetails.getAvailability());
            book.setPhoto(bookDetails.getPhoto());

            Book updatedBook = bookRepository.save(book);
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un livre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Méthode pour encoder l'image en base64 si nécessaire
    private String encodeImage(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }
}
