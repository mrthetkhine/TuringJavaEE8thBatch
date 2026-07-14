import { computed, inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Movie } from '../../../shared/models/movie.model';
import { ApiResponse } from '../../../shared/models/api-response.model';
const BASE_URL = 'http://localhost:8080/api';
export interface MovieState {
  movies: Movie[];
  loading: boolean;
}
@Injectable({
  providedIn: 'root',
})
export class MovieService {
  private http = inject(HttpClient);
  private state = signal<MovieState>({
    movies: [],
    loading: false
  });
  public movies = computed(() => this.state().movies);

  constructor() {
    this.loadAllMovies();
  }

  loadAllMovies() {
    this.http.get<ApiResponse<Movie[]>>(BASE_URL+'/movies')
      .subscribe(response => {
        //console.log('load all actors ', response);
        this.initMovies(response.data);
      });
  }
  initMovies(movies: Movie[]) {
    this.state.update((currentState) => ({
      loading:false,
      movies: movies
    }));
  }
}
