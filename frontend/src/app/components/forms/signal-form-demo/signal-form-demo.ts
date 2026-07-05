import { Component, signal } from '@angular/core';
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

function GenreSchema(item: SchemaPathTree<string>) {
  required(item, {message: 'Genre is required'});
  minLength(item, 3, {message: 'Genre must be at least 31'});
}

@Component({
  selector: 'app-signal-form-demo',
  imports: [FormField, ReactiveFormsModule, FormRoot, FormsModule],
  templateUrl: './signal-form-demo.html',
  styleUrl: './signal-form-demo.css',
})
export class SignalFormDemo {
  movieModel = signal<Movie>({
    id: 0,
    title: '',
    year: 0,
    genres:[],
  });
  movieForm = form(this.movieModel, (schemaPath) => {
  required(schemaPath.title, {message: 'Title is required'});
  minLength(schemaPath.title,4,{message:'Title should be at least 4 characters'});
  required(schemaPath.year, {message: 'Year is required'});
  min(schemaPath.year, 1000, {message: 'Year must be at least 1000 years old'});
  applyEach(schemaPath.genres, (genre) => {
      required(genre);
      minLength(genre, 3);
    });
});
  onSubmit() {
    const formData = this.movieModel();
    console.log('Form Data: ', formData);
    // Send to server
  }
  update()
  {
    this.movieModel.set({
      title:'Title update',
      year: 2010,
      genres:[],
    });
  }
}
