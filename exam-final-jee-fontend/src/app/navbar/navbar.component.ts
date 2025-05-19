import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: false,
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  constructor(/**protected authService: AuthService , **/ private router :Router) {
  }

  handleLogout() {
    console.log("logggout action")
    // this.authService.logout()
  }
}