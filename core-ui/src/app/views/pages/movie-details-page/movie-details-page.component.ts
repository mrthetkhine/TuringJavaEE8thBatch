import { Component, inject } from '@angular/core';
import { RowComponent } from '@coreui/angular';
import { ActivatedRoute } from '@angular/router';
import { Movie } from '../../../shared/models/movie.model';
import { MovieService } from '../../../features/movie/services/movie.service';

@Component({
  selector: 'app-movie-details-page',
  imports: [
    RowComponent
  ],
  templateUrl: './movie-details-page.component.html',
  styleUrl: './movie-details-page.component.scss',
})
export class MovieDetailsPageComponent {
  private activatedRoute = inject(ActivatedRoute);
  movieService = inject(MovieService);

  movieId?: string;
  movie?:Movie;
  constructor() {
    this.movieId = this.activatedRoute.snapshot.paramMap.get('id') ?? '';
    console.log('Movie Details Page', this.movieId);
    this.movie = this.movieService.getMovieById(this.movieId);
    console.log('Movie Details movie', this.movie);
  }
}
