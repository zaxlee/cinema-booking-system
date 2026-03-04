import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './movie-list.html',
  styleUrl: './movie-list.css'
})
export class MovieList implements OnInit {

  movies: any[] = [];

  newTitle = '';
  newShowTime = '';
  newTotalSeats = 0;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies() {

    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    this.loading = true;

    this.http.get<any[]>('http://localhost:8080/movies', { headers })
      .subscribe(data => {
        this.movies = data;
        this.loading = false;
      });
  }

  addMovie() {

    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    const movie = {
      title: this.newTitle,
      showTime: this.newShowTime,
      totalSeats: this.newTotalSeats
    };

    this.http.post<any>('http://localhost:8080/movies', movie, { headers })
      .subscribe((createdMovie) => {

        this.movies.push(createdMovie);

        this.newTitle = '';
        this.newShowTime = '';
        this.newTotalSeats = 0;

      });
  }

  deleteMovie(id: number) {

    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    this.http.delete(`http://localhost:8080/movies/${id}`, { headers })
      .subscribe(() => {

        this.movies = this.movies.filter(m => m.id !== id);

      });
  }

bookSeat(movieId: number) {

  const token = localStorage.getItem('token');

  const headers = new HttpHeaders({
    Authorization: `Bearer ${token}`
  });

  const booking = {
    movieId: movieId,
    seatNumber: Math.floor(Math.random() * 100) + 1
  };

  this.http.post('http://localhost:8080/bookings', booking, { headers })
    .subscribe(() => {

      alert("Seat booked successfully!");

      this.loadMovies();

    });
}
}
