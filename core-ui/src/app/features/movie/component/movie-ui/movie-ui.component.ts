import { Component, inject, input, output } from '@angular/core';
import { Movie } from '../../../../shared/models/movie.model';
import { ButtonDirective } from '@coreui/angular';
import { Router } from '@angular/router';

@Component({
  selector: 'tr[app-movie-ui]',
  imports: [
    ButtonDirective
  ],
  templateUrl: './movie-ui.component.html',
  styleUrl: './movie-ui.component.scss',
})
export class MovieUIComponent {
  movie = input.required<Movie>();
  onEdit = output<Movie>();
  onDelete = output<Movie>();
  router = inject(Router);
  onEditClick() {
    this.onEdit.emit(this.movie());
  }
  onDeleteClick() {
    this.onDelete.emit(this.movie());
  }
  goToMovieDetails() {
    console.log('goToMovieDetails');
    let m = this.movie();
    this.router.navigate([`movies/${m?.id}`]);
  }
}
