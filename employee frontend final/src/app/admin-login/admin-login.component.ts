import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent {
  fname: string = '';
  lname: string = '';

  constructor(private router: Router, private authService: AuthService) {}

  login() {
    this.authService.validateUser(this.fname, this.lname).subscribe(
      (isValid) => {
        if (isValid) {
          // Redirection vers la page d'accueil si le login est réussi
          this.router.navigate(['/home']);
        } else {
          alert('Remplissez les champs correctement.');
        }
      },
      (error) => {
        console.error('Erreur lors de la vérification des identifiants', error);
        alert('Une erreur est survenue. Veuillez réessayer.');
      }
    );
  }
}
