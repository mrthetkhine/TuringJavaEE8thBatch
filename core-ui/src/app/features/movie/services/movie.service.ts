import { computed, inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Movie } from '../../../shared/models/movie.model';
import { ApiResponse } from '../../../shared/models/api-response.model';
import {BASE_URL} from '../../../shared/util/Config';

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
    this.apiLoadAllMovies();
  }

  apiLoadAllMovies() {
    this.http.get<ApiResponse<Movie[]>>(BASE_URL+'/movies')
      .subscribe(response => {
        this.initMovies(response.data);
      });
  }
  initMovies(movies: Movie[]) {
    this.state.update((currentState) => ({
      loading:false,
      movies: movies
    }));
  }
  getMovieById(id: string) {
    return this.http.get<ApiResponse<Movie>>(BASE_URL+`/movies/${id}`);
  }
  apiSaveMovie(movie: Movie) {
    delete movie.id;
    this.http.post<ApiResponse<Movie>>(BASE_URL+'/movies', JSON.stringify(movie),
      {headers: { 'Content-Type': 'application/json' }})
      .subscribe(response => {
        this.addMovie(response.data);
      });
  }
  addMovie(movie: Movie) {
    this.state.update((currentState) => ({
      ...currentState,
      movies: [...currentState.movies,movie]
    }));
  }

  apiDeleteMovie(movie: Movie) {
    this.http.delete<ApiResponse<Movie>>(BASE_URL+`/movies/${movie.id}`)
      .subscribe(response => {
        this.deleteMovie(response.data);
      });
  }
  deleteMovie(movie: Movie) {
    this.state.update((currentState) => ({
      ...currentState,
      movies: currentState.movies.filter(m => movie.id !== m.id)
    }));
  }
  apiUpdateMovie(movie: Movie) {

    this.http.put<ApiResponse<Movie>>(BASE_URL+`/movies/${movie.id}`, JSON.stringify(movie),
      {headers: { 'Content-Type': 'application/json' }})
      .subscribe(response => {
        this.updateMovie(response.data);
      });
  }
  updateMovie(movie: Movie) {
    this.state.update((currentState) => ({
      ...currentState,
      movies: currentState.movies.map(m=>m.id==movie.id?movie:m)
    }));
  }
  apiAssignActorToMovie(movieId:string,actorId:string) {
    return this.http.post<ApiResponse<Movie>>(BASE_URL+`/movies/${movieId}/assignment/actors/${actorId}`, JSON.stringify({}),
      {headers: { 'Content-Type': 'application/json' }});

  }
}
