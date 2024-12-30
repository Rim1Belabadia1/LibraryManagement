import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmpruntService } from '../../services/emprunt.service';
import { Emprunt } from '../../models/emprunt.model';

@Component({
  selector: 'app-emprunt-form',
  templateUrl: './emprunt-form.component.html',
  styleUrls: ['./emprunt-form.component.css']
})
export class EmpruntFormComponent {
  empruntForm: FormGroup; // Formulaire réactif
  successMessage: string | null = null; // Message de succès
  errorMessage: string | null = null; // Message d'erreur

  constructor(private fb: FormBuilder) {
    // Initialisation du formulaire avec les contrôles nécessaires
    this.empruntForm = this.fb.group({
      nomLivre: ['', [Validators.required, Validators.minLength(3)]],
      fullName: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]+$')]],
      dateAcquisition: ['', Validators.required],
      dateRetour: ['', Validators.required],
    });
    this.empruntForm.patchValue({
      nomLivre: 'Le Petit Prince'
  });
  this.empruntForm.patchValue({
    fullName: 'Test Test'
});
  }

  onSubmit(): void {
    if (this.empruntForm.valid) {
      // Logique après soumission réussie
      console.log(this.empruntForm.value);
      this.successMessage = 'Formulaire soumis avec succès !';
      this.errorMessage = null;
      this.empruntForm.reset(); // Réinitialiser le formulaire
    } else {
      this.successMessage = null;
      this.errorMessage = 'Veuillez corriger les erreurs avant de soumettre.';
    }
  }
}
