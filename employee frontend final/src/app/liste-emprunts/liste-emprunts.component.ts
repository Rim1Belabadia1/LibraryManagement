import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-liste-emprunts',
  templateUrl: './liste-emprunts.component.html',
  styleUrls: ['./liste-emprunts.component.css']
})
export class ListeEmpruntsComponent implements OnInit {
  emprunts: any[] = [];  // Déclaration de la variable emprunts
  empruntAModifier: any = null;  // Variable pour stocker l'emprunt à modifier

  constructor() { }

  ngOnInit(): void {
    // Simuler la récupération des emprunts depuis un service ou une source de données
    setTimeout(() => {
      this.emprunts = [
        {
          id: 1,
          nomUtilisateur: 'Test Test',
          nomLivre: 'Le Petit Prince',
          dateAcquisition: '2024-01-15',
          dateRetour: '2024-02-15',
          etat: 'Empreinté'
        },
      ];
    }, 2000);  // Simuler un délai de chargement
  }

  // Méthode pour supprimer un emprunt
  supprimerEmprunt(id: number): void {
    this.emprunts = this.emprunts.filter(emprunt => emprunt.id !== id);
  }

  // Méthode pour afficher le formulaire de modification d'un emprunt
  modifierEmprunt(emprunt: any): void {
    this.empruntAModifier = { ...emprunt };  // Crée une copie de l'emprunt à modifier
  }

  // Méthode pour sauvegarder les modifications
  sauvegarderModification(): void {
    const index = this.emprunts.findIndex(e => e.id === this.empruntAModifier.id);
    if (index !== -1) {
      this.emprunts[index] = { ...this.empruntAModifier };  // Met à jour l'emprunt modifié
    }
    this.empruntAModifier = null;  // Réinitialiser l'emprunt à modifier
  }

  // Méthode pour annuler la modification
  annulerModification(): void {
    this.empruntAModifier = null;  // Réinitialiser l'emprunt à modifier
  }
  // Méthode pour valider la cohérence des dates
dateRetourInvalide(): boolean {
  return (
    this.empruntAModifier?.dateAcquisition &&
    this.empruntAModifier?.dateRetour &&
    new Date(this.empruntAModifier.dateRetour) < new Date(this.empruntAModifier.dateAcquisition)
  );
}

}

