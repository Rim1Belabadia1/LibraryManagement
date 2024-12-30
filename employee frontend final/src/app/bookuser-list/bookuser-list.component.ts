import { Component, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './bookuser-list.component.html',
  styleUrls: ['./bookuser-list.component.css']
})
export class BookUserListComponent implements OnInit {
  books: any[] = [];
  searchTerm: string = ''; // Variable liée à la zone de recherche

  constructor(private bookService: BookService) {}

  getPhotoBase64(photo: any): string {
    if (photo && typeof photo === 'string' && photo.length > 0) {
      return 'data:image/png;base64,' + photo; // Modifier selon le format de l'image (JPEG ou PNG)
    }
    return ''; // Retourne une chaîne vide si l'image n'est pas disponible
  }

  ngOnInit(): void {
    this.bookService.getBooks().subscribe(
      (data) => {
        this.books = data;
        console.log(this.books); // Debugging: Afficher les livres récupérés pour vérifier les données photo
      },
      (error) => {
        console.error('Erreur lors de la récupération des livres', error);
      }
    );
  }

  // Fonction de filtrage par catégorie, auteur, type et éditeur
  filteredBooks() {
    return this.books.filter(book =>
      // Recherche par catégorie, auteur, type ou éditeur (insensible à la casse)
      (book.categorie && book.categorie.toLowerCase().includes(this.searchTerm.toLowerCase())) ||
      (book.auteur && book.auteur.toLowerCase().includes(this.searchTerm.toLowerCase())) ||
      (book.type && book.type.toLowerCase().includes(this.searchTerm.toLowerCase())) ||
      (book.editeur && book.editeur.toLowerCase().includes(this.searchTerm.toLowerCase()))
    );
  }

  borrowBook(book: any) {
    book.availability = 'Non';

    this.bookService.updateBookAvailability(book.id, 'Non').subscribe(
      (response) => {
        console.log('Livre emprunté avec succès', response);
      },
      (error) => {
        console.error('Erreur lors de l\'emprunt du livre', error);
      }
    );
  }
}
