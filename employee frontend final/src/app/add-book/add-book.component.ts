import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookService } from '../book.service';
import { Book } from '../book.model';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
})
export class AddBookComponent {
  bookForm: FormGroup;

  constructor(private fb: FormBuilder, private bookService: BookService) {
    this.bookForm = this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      description: [''],
      editeur: ['', Validators.required],
      auteur: ['', Validators.required],
      dateDePublication: ['', Validators.required],
      photo: [null],  // Utiliser null pour un fichier
      availability: ['Oui', Validators.required],
    });
  }

  onFileSelected(event: Event): void {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        // Convertir l'image en Base64
        const base64String = (reader.result as string).split(',')[1]; // Prendre la partie Base64 de l'URL
        this.bookForm.patchValue({
          photo: base64String  // Stocker la chaîne Base64
        });
      };
      reader.readAsDataURL(file);  // Lire le fichier comme URL de données
    }
  }


  onSubmit() {
    if (this.bookForm.valid) {
      const formValue = this.bookForm.value;
      const newBook: Book = {
        ...formValue,
        photo: formValue.photo, // Utiliser directement le tableau d'octets
      };
      this.bookService.addBook(newBook).subscribe(
        (response) => {
          console.log('Livre ajouté avec succès!', response);
          this.bookForm.reset();
        },
        (error) => {
          console.error("Erreur lors de l'ajout du livre", error);
        }
      );
    }
  }


}
