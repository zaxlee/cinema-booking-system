import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { MovieList } from './components/movie-list/movie-list';

export const routes: Routes = [
  { path: '', component: Login },
  { path: 'movies', component: MovieList }
];
