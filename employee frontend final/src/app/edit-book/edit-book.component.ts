import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookService } from '../book.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {
  bookForm: FormGroup;
  bookId: number = 0;
  fileError: string | null = null;

  constructor(
    private fb: FormBuilder,
    private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.bookForm = this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      auteur: ['', Validators.required],
      availability: ['Oui', Validators.required],
      editeur: ['', Validators.required],
      dateDePublication: ['', Validators.required],
      description: [''],
      photo: ['']
    });
  }

  ngOnInit(): void {
    this.bookId = +this.route.snapshot.paramMap.get('id')!;
    this.loadBook();
  }

  loadBook() {
    this.bookService.getBookById(this.bookId).subscribe(book => {
      this.bookForm.patchValue(book);
    }, error => {
      console.error('Erreur de chargement:', error);
      alert('Impossible de charger les données du livre.');
    });
  }

  onSubmit() {
    if (this.bookForm.valid) {
      const formValue = this.bookForm.value;
      if (formValue.photo instanceof File) {
        this.convertFileToBase64(formValue.photo).then(base64Image => {
          formValue.photo = base64Image;
          this.updateBook(formValue);
        }).catch(() => {
          alert('Erreur lors de la conversion de l\'image.');
        });
      } else {
        this.updateBook(formValue);
      }
    } else {
      alert('Veuillez corriger les erreurs dans le formulaire.');
    }
  }

  updateBook(bookData: any) {
    this.bookService.updateBook(this.bookId, bookData).subscribe(() => {
      alert('Livre modifié avec succès.');
      this.router.navigate(['/books']);
    }, error => {
      console.error('Erreur de mise à jour:', error);
      alert('Erreur lors de la modification du livre.');
    });
  }

  convertFileToBase64(file: File): Promise<string> {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.onloadend = () => resolve(reader.result as string);
      reader.onerror = () => reject();
      reader.readAsDataURL(file);
    });
  }

  onFileChange(event: any): void {
    const file = event.target.files[0];
    if (file && !file.type.startsWith('image/')) {
      this.fileError = 'Seuls les fichiers d\'image sont autorisés.';
      return;
    }
    this.fileError = null;

    const reader = new FileReader();
    reader.onloadend = () => {
      const base64String = reader.result as string;
      const base64Data = base64String.split(',')[1];
      this.bookForm.patchValue({ photo: base64Data });
    };

    if (file) {
      reader.readAsDataURL(file);
    }
  }
}
