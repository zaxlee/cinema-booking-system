import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private API_URL = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');

    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  getMovies(): Observable<any[]> {
    return this.http.get<any[]>(`${this.API_URL}/movies`, {
      headers: this.getHeaders()
    });
  }

  addMovie(movie: any): Observable<any> {
    return this.http.post(`${this.API_URL}/movies`, movie, {
      headers: this.getHeaders()
    });
  }

  deleteMovie(id: number): Observable<any> {
    return this.http.delete(`${this.API_URL}/movies/${id}`, {
      headers: this.getHeaders()
    });
  }

  bookSeat(movieId: number): Observable<any> {

    const booking = {
      movieId: movieId,
      seatNumber: Math.floor(Math.random() * 100) + 1
    };

    return this.http.post(`${this.API_URL}/bookings`, booking, {
      headers: this.getHeaders()
    });
  }

}
