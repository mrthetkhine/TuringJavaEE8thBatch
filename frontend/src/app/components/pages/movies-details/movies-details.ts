import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-movies-details',
  imports: [RouterLink],
  templateUrl: './movies-details.html',
  styleUrl: './movies-details.css',
})
export class MoviesDetails {
  private activatedRoute = inject(ActivatedRoute);
  movieId?: string;

  constructor() {
    this.movieId = this.activatedRoute.snapshot.paramMap.get('id') ?? '';
    console.log('Movie Details Page', this.movieId);
  }
}
