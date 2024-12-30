import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isAdmin: boolean = false;
  isUser: boolean = true;

  constructor(private authService: AuthService) {
    this.authService.isAdmin$.subscribe((isAdmin: boolean) => {
      this.isAdmin = isAdmin;
    });
    this.authService.isUser$.subscribe((isUser: boolean) => {
      this.isUser = isUser;
    });
  }
}
