import { Component, inject, signal, viewChild } from '@angular/core';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  CardHeaderComponent,
  ColComponent,
  ColDirective,
  FormControlDirective,
  FormDirective, FormFeedbackComponent,
  FormLabelDirective,
  GutterDirective, ModalBodyComponent, ModalComponent, ModalFooterComponent, ModalHeaderComponent, ModalTitleDirective,
  RowComponent,
  RowDirective,
  TableDirective
} from '@coreui/angular';
import { DocsComponentsComponent } from '@docs-components/docs-components/docs-components.component';
import { DocsExampleComponent } from '@docs-components/docs-example/docs-example.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthUser } from '../../../shared/models/auth-user.model';
import { Movie } from '../../../shared/models/movie.model';
import { applyEach, form, FormField, FormRoot, minLength, required } from '@angular/forms/signals';
import { MovieService } from '../../../features/movie/services/movie.service';
import { MovieUIComponent } from '../../../features/movie/component/movie-ui/movie-ui.component';
import { ConfirmDialogComponent } from '../../../shared/component/confirm-dialog/confirm-dialog.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-page',
  imports: [RowComponent, ColComponent, CardComponent, CardHeaderComponent, CardBodyComponent, DocsExampleComponent, ReactiveFormsModule, FormsModule, FormDirective, FormLabelDirective, FormControlDirective, ButtonDirective, RowDirective, GutterDirective, ColDirective, DocsComponentsComponent, TableDirective, ModalComponent, ModalHeaderComponent, ModalBodyComponent, ModalFooterComponent, ModalTitleDirective, FormRoot, FormField, FormFeedbackComponent, MovieUIComponent, ConfirmDialogComponent],
  templateUrl: './movie-page.component.html',
  styleUrl: './movie-page.component.scss',
})
export class MoviePageComponent {
  public favoriteColor = signal('#26ab3c');
  public visible = false;

  movieService = inject(MovieService);
  dlgDeleteConfirm = viewChild(ConfirmDialogComponent);
  movieModel = signal<Movie>({
    id:'',
    title: '',
    year: 0,
    director: '',
    genres: [],
    details:{
      details:''
    },
  });
  movieForm = form(this.movieModel, (schemaPath) => {
    required(schemaPath.title, { message: 'Title is required' });
    minLength(schemaPath.title,3, { message: 'Title must be at least 3 characters' });
    required(schemaPath.year, { message: 'Year is required' });
    required(schemaPath.director, { message: 'Director is required' });
    //required(schemaPath.year, { message: 'Year is required' });
    required(schemaPath.details.details, { message: 'Details is required' });
    applyEach(schemaPath.genres, (genre) => {
      required(genre,{ message: 'Genre is required' });
      minLength(genre, 3,{ message: 'Genre must be at least 3 character' });
    });
  },{
    submission: {
      action: async (form) => {
        // Handle your API call or submission logic here
        let movie = form().value();
        if(this.editMode)
        {
          this.editMovie(movie);
        }
        else
        {
          this.saveMovie(movie);
        }

      }
    }
  });
  editMode = false;
  handleLiveDemoChange(event: any) {
    this.visible = event;
  }

  private emptyMovie:Movie = {
    id:'',
    title:'',
    year:0,
    director:'',
    genres:[],
    details: {
      details:''
    },
  };

  openNewMovieDialog() {
    this.editMode= false;
    this.movieModel.set(this.emptyMovie);
    this.movieForm().reset();
    this.openModal();
  }
  openModal()
  {
    this.visible = true;
  }
  closeModal()
  {
    this.visible = false;
  }
  removeGenre(index: number)
  {
    console.log('Index ',index);
    this.movieModel.update(state => ({
      ...state,
      genres: state.genres.filter((_, i) => i !== index)
    }));
  }
  addGenre()
  {
    console.log('add Genre');
    this.movieModel.update(state => ({
      ...state,
      genres: [...state.genres, ''] // Appends an empty element
    }));
  }
  onEditHandler(movie: Movie): void {
    console.log('onEdit ',movie);
    this.editMode = true;
    this.movieModel.set({
      id:movie.id,
      title:movie.title,
      year:movie.year,
      director:movie.director,
      genres:movie.genres,
      details:movie.details,
    })
    this.openModal();
  }
  onDeleteHandler(movie: Movie): void {
    console.log('onDelete ',movie);
    this.dlgDeleteConfirm()?.openConfirm(()=>{
      console.log('Delete confirm ');
      this.movieService.apiDeleteMovie(movie);
    });
  }
  editMovie(movie:Movie)
  {
    console.log('Edit movie', movie);
    this.movieService.apiUpdateMovie(movie);
    this.closeModal();
  }
  saveMovie(movie:Movie)
  {
    console.log('Save movie', movie);
    this.movieService.apiSaveMovie(movie);
    this.closeModal();
  }
}
