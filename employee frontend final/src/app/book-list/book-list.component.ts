import { Component, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  books: any[] = [];

  constructor(private bookService: BookService) {}
  getPhotoBase64(photo: any): string {
    if (photo && typeof photo === 'string' && photo.length > 0) {
      // Assurez-vous que la chaîne Base64 commence avec le bon type MIME
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

  deleteBook(id: number): void {
    if (confirm("Êtes-vous sûr de vouloir supprimer ce livre ?")) {
      this.bookService.deleteBook(id).subscribe(() => {
        // Filtrer le livre supprimé de la liste
        this.books = this.books.filter(book => book.id !== id);
        alert('Livre supprimé avec succès.');
      }, error => {
        console.error('Erreur lors de la suppression du livre:', error);
        alert('Erreur lors de la suppression du livre.');
      });
}
  }}
