import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Emprunt } from '../models/emprunt.model';

@Injectable({
  providedIn: 'root'
})
export class EmpruntService {
  private apiUrl = 'http://localhost:8080/api/emprunts';  // Remplacez par l'URL de votre API backend

  constructor(private http: HttpClient) { }

  // Méthode pour ajouter un emprunt
  addEmprunt(emprunt: Emprunt): Observable<Emprunt> {
    return this.http.post<Emprunt>(this.apiUrl, emprunt);
  }
  getEmprunts(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
  ajouterEmprunt(empruntData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/emprunts`, empruntData);
}

}
