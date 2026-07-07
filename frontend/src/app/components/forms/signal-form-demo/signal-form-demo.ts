import { Component, signal, inject, TemplateRef } from '@angular/core';
import {
  form,
  FormField,
  required,
  email,
  FormRoot,
  min,
  minLength,
  SchemaPathTree,
  applyEach
} from '@angular/forms/signals';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Movie } from '../../../models/movie.model';
import { ModalDismissReasons, NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap/modal';

function GenreSchema(item: SchemaPathTree<string>) {
  required(item, {message: 'Genre is required'});
  minLength(item, 3, {message: 'Genre must be at least 31'});
}

@Component({
  selector: 'app-signal-form-demo',
  imports: [FormField, ReactiveFormsModule, FormRoot, FormsModule],
  templateUrl: './signal-form-demo.html',
  styleUrl: './signal-form-demo.css',
  providers: [NgbModalConfig, NgbModal],
})
export class SignalFormDemo {
  movieModel = signal<Movie>({
    id: 0,
    title: '',
    year: 0,
    genres: [],
  });
  movieForm = form(this.movieModel, (schemaPath) => {
    required(schemaPath.title, { message: 'Title is required' });
    minLength(schemaPath.title, 4, { message: 'Title should be at least 4 characters' });
    required(schemaPath.year, { message: 'Year is required' });
    min(schemaPath.year, 1000, { message: 'Year must be at least 1000 years old' });
    applyEach(schemaPath.genres, (genre) => {
      required(genre,{ message: 'Genre is required' });
      minLength(genre, 3,{ message: 'Genre must be at least 3 character' });
    });
  });

  private readonly modalService = inject(NgbModal);
  readonly closeResult = signal('');

  modalRef?:NgbModalRef ;

  open(content: TemplateRef<any>) {
    this.modalRef = this.modalService.open(content, {
                                                    ariaLabelledBy: 'modal-basic-title',
                                                    size: 'lg'
    });

  }

  onSubmit() {
    const formData = this.movieModel();
    console.log('Form Data: ', formData);
    // Send to server
    this.modalRef?.close();
  }

  update() {
    this.movieModel.set({
      title: 'Title update',
      year: 2010,
      genres: [],
    });
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
    this.movieModel.update(state => ({
      ...state,
      genres: [...state.genres, ''] // Appends an empty element
    }));
  }
}
