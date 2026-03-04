import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { MovieService } from '../../services/movie';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './movie-list.html',
  styleUrl: './movie-list.css'
})
export class MovieList implements OnInit {

  movies: any[] = [];
  loading = false;
  role: string = '';

  newTitle = '';
  newShowTime = '';
  newTotalSeats = 0;

  constructor(
    private movieService: MovieService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    const token = localStorage.getItem('token');

    if (token) {
      const decoded: any = jwtDecode(token);
      this.role = decoded.role || decoded.roles || '';
    }

    this.loadMovies();
  }

  loadMovies() {

    this.loading = true;

    this.movieService.getMovies()
      .subscribe({
        next: (data) => {
          this.movies = data;
          this.loading = false;

          this.cdr.detectChanges();
        },
        error: (err) => {
          console.error("Failed to load movies", err);
          this.loading = false;

          this.cdr.detectChanges();
        }
      });

  }

  addMovie() {

    const movie = {
      title: this.newTitle,
      showTime: this.newShowTime,
      totalSeats: this.newTotalSeats
    };

    this.movieService.addMovie(movie)
      .subscribe(() => {

        this.newTitle = '';
        this.newShowTime = '';
        this.newTotalSeats = 0;

        this.loadMovies();

      });

  }

  deleteMovie(id: number) {

    this.movieService.deleteMovie(id)
      .subscribe(() => {
        this.loadMovies();
      });

  }

  bookSeat(movieId: number) {

    this.movieService.bookSeat(movieId)
      .subscribe(() => {

        alert("Seat booked successfully!");

        this.loadMovies();

      });

  }

  logout() {

    localStorage.removeItem('token');
    this.router.navigate(['/']);

  }

}
