import { Component, inject } from '@angular/core';
import { Movie } from '../../../models/movie.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movies-list',
  imports: [],
  templateUrl: './movies-list.html',
  styleUrl: './movies-list.css',
})
export class MoviesList {
  movies: Movie[] =[
    {
      id: 1,
      title:'Titanic',
      year: 1995,
      genres:[],
    },
    {
      id: 2,
      title:'The Dark Knight',
      year: 1995,
      genres:[],
    },
    {
      id: 3,
      title:'X Men',
      year: 2001,
      genres:[],
    }
  ]
  private router = inject(Router);
  goToDetails(id?: number){
    console.log('Go to details ',id);
    this.router.navigate([`/movies/${id}`]);
  }
}
