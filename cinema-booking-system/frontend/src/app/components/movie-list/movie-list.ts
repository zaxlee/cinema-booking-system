import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './movie-list.html',
  styleUrl: './movie-list.css'
})
export class MovieList implements OnInit {

  movies: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies() {
    this.http.get<any[]>('http://localhost:8080/movies')
      .subscribe(data => {
        this.movies = data;
      });
  }
}
