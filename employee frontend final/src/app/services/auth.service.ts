import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/v1'; // URL de base du backend
  private isAdminSubject = new BehaviorSubject<boolean>(true); // État de l'admin
  private isUserSubject = new BehaviorSubject<boolean>(true); // État de l'utilisateur normal

  isAdmin$ = this.isAdminSubject.asObservable();
  isUser$ = this.isUserSubject.asObservable();

  constructor(private http: HttpClient) {}

  setIsAdmin(isAdmin: boolean) {
    this.isAdminSubject.next(isAdmin);
  }

  setIsUser(isUser: boolean) {
    this.isUserSubject.next(isUser);
  }

  validateUser(fname: string, lname: string): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/employees/login`, { fname, lname });
  }

  login(fname: string, lname: string) {
    if (fname === 'admin' && lname === 'admin') {
      this.setIsAdmin(true);
      this.setIsUser(false);
    } else {
      this.setIsAdmin(false);
      this.setIsUser(true);
    }
  }

  logout() {
    this.setIsAdmin(false);
    this.setIsUser(false);
  }
}
