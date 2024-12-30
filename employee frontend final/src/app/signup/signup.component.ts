import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Importer le module Router

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router) {} // Injecter Router dans le constructeur

  signup() {
    // Logique d'inscription (ajouter ici la logique d'enregistrement des utilisateurs)
    // Pour cet exemple, nous allons simplement afficher un message d'alerte

    if (this.username && this.password) {
      alert(`Inscription réussie pour ${this.username}`);
      this.router.navigate(['/']); // Redirige vers la page d'accueil après l'inscription
    } else {
      alert('Veuillez remplir tous les champs.');
    }
  }
}
