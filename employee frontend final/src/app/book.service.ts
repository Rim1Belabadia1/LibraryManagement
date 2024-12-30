import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from './book.model'; // Assurez-vous d'importer votre modèle Book

@Injectable({
  providedIn: 'root',
})
export class BookService {
  private apiUrl = 'http://localhost:8080/books'; // Remplacez par l'URL de votre API

  constructor(private http: HttpClient) {}

  // Méthode pour ajouter un livre
  addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(this.apiUrl, book, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json', // Assurez-vous d'envoyer les données en JSON
      }),
    });
  }

  // Méthode pour récupérer tous les livres
  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.apiUrl);
  }

  // Méthode pour récupérer un livre par son ID
  getBookById(id: number): Observable<Book> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Book>(url);
  }

  // Méthode pour mettre à jour un livre
  updateBook(id: number, book: Book): Observable<Book> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.put<Book>(url, book, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    });
  }

  // Méthode pour supprimer un livre
  deleteBook(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
