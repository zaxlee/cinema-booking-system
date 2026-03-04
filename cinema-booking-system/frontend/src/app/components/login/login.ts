import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  username = '';
  password = '';
  errorMessage = '';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post<any>('http://localhost:8080/auth/login', {
      username: this.username,
      password: this.password
    }).subscribe({
      next: (response) => {
        localStorage.setItem('token', response.token);
        this.router.navigate(['/movies']);
      },
      error: () => {
        this.errorMessage = 'Invalid username or password';
      }
    });
  }
}
