import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './movie-list.html',
  styleUrl: './movie-list.css'
})
export class MovieList implements OnInit {

  movies: any[] = [];
  loading = true;

  role: string = '';

  newTitle = '';
  newShowTime = '';
  newTotalSeats = 0;

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {

    const token = localStorage.getItem('token');

    if (token) {
      const decoded: any = jwtDecode(token);
      this.role = decoded.role || decoded.roles || '';
    }

    this.loadMovies();
  }

  getHeaders() {
    const token = localStorage.getItem('token');

    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  loadMovies() {

    this.loading = true;

    this.http.get<any[]>('http://localhost:8080/movies', {
      headers: this.getHeaders()
    })
    .subscribe({
      next: (data) => {
        this.movies = data;
        this.loading = false;
      },
      error: (err) => {
        console.error("Failed to load movies", err);
        this.loading = false;
      }
    });

  }

  addMovie() {

    const movie = {
      title: this.newTitle,
      showTime: this.newShowTime,
      totalSeats: this.newTotalSeats
    };

    this.http.post<any>('http://localhost:8080/movies', movie, {
      headers: this.getHeaders()
    })
    .subscribe({
      next: (createdMovie) => {

        this.movies.push(createdMovie);

        this.newTitle = '';
        this.newShowTime = '';
        this.newTotalSeats = 0;

      },
      error: (err) => {
        console.error("Failed to add movie", err);
      }
    });

  }

  deleteMovie(id: number) {

    this.http.delete(`http://localhost:8080/movies/${id}`, {
      headers: this.getHeaders()
    })
    .subscribe({
      next: () => {

        this.movies = this.movies.filter(m => m.id !== id);

      },
      error: (err) => {
        console.error("Failed to delete movie", err);
      }
    });

  }

  bookSeat(movieId: number) {

    const booking = {
      movieId: movieId,
      seatNumber: Math.floor(Math.random() * 100) + 1
    };

    this.http.post('http://localhost:8080/bookings', booking, {
      headers: this.getHeaders()
    })
    .subscribe({
      next: () => {

        alert("Seat booked successfully!");

        this.loadMovies();

      },
      error: (err) => {
        console.error("Booking failed", err);
      }
    });

  }

  logout() {

    localStorage.removeItem('token');
    this.router.navigate(['/']);

  }

}
