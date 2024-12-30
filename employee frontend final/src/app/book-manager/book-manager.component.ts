import { Component, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';
import { Book } from '../models/book.model';

@Component({
  selector: 'app-book-manager',
  templateUrl: './book-manager.component.html',
  styleUrls: ['./book-manager.component.css']
})
export class BookManagerComponent implements OnInit {
  books: Book[] = [];
  book: Book = {
    id: 0,
    title: '',
    publisher: '',
    publicationYear: 0,
    isbn: '',
    genre: '',
    language: '',
    pageCount: 0,
    summary: '',
    acquisitionDate: new Date().toISOString().substring(0, 10),
    condition: '',
    availability: true,
    location: '',
    keywords: ''
  };

  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.bookService.getBooks().subscribe((data: Book[]) => {
      this.books = data;
    });
  }

  onSubmit(): void {
    if (this.book.id === 0) {
      this.bookService.addBook(this.book).subscribe(() => {
        this.loadBooks();
        this.resetForm();
      });
    } else {
      this.bookService.updateBook(this.book).subscribe(() => {
        this.loadBooks();
        this.resetForm();
      });
    }
  }

  deleteBook(id: number): void {
    this.bookService.deleteBook(id).subscribe(() => {
      this.loadBooks();
    });
  }

  resetForm(): void {
    this.book = {
      id: 0,
      title: '',
      publisher: '',
      publicationYear: 0,
      isbn: '',
      genre: '',
      language: '',
      pageCount: 0,
      summary: '',
      acquisitionDate: new Date().toISOString().substring(0, 10),
      condition: '',
      availability: true,
      location: '',
      keywords: ''
    };
  }

  editBook(book: Book): void {
    this.book = { ...book };
  }
}
