import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Movie } from '../../../models/movie.model';

@Component({
  selector: 'app-template-driven-form-demo',
  imports: [
    FormsModule,
  ],
  templateUrl: './template-driven-form-demo.html',
  styleUrl: './template-driven-form-demo.css',
})
export class TemplateDrivenFormDemo {
  movie:Movie = {
    id:1,
    title:'',
    year:0
  }
  onSubmit() {
    console.log('movie ',this.movie);
  }
}
