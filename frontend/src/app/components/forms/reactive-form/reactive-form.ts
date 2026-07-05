import { Component, inject } from '@angular/core';
import {
  AbstractControl, FormArray,
  FormBuilder,
  FormControl,
  FormsModule,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators
} from '@angular/forms';

export function upperCaseValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    //{forbiddenName: {value: control.value}}
    const input = control.value??'';
    if (input.length > 1) {
      let upperCaseFirstChar = input.charAt(0).toUpperCase();
      if(upperCaseFirstChar !== input.charAt(0)) {
        return {upperCase: {value: control.value}}
      }
      else {
        return null;
      }
    }
    else {
      return {upperCase: {value: control.value}}
    }

  };
}
@Component({
  selector: 'app-reactive-form',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './reactive-form.html',
  styleUrl: './reactive-form.css',
})
export class ReactiveForm {
  private formBuilder = inject(FormBuilder);
  /*title = new FormControl('');
  year = new FormControl('');*/

  movieForm = this.formBuilder.group({
    title: ['',[
      Validators.required,
      Validators.minLength(4),
      upperCaseValidator()
    ]],
    year: [0,Validators.required],
    genres: this.formBuilder.array([]),
    /*address: this.formBuilder.group({
      street: [''],
      city: [''],
      state: [''],
      zip: [''],
    }),*/
  });

  updateTitle()
  {
    this.movieForm.patchValue({
      ...this.movieForm.value,
      title:'Title update'
    })
  }
  onSubmit()
  {
    console.log('Movie form ',this.movieForm.value);
  }
  get title()
  {
    return this.movieForm.get('title');
  }
  get year()
  {
    return this.movieForm.get('year');
  }
  get genres() {
    return this.movieForm.get('genres') as FormArray;
  }
  addGenre()
  {
    console.log('add genre');
    this.genres.push(this.formBuilder.control('',[Validators.required]) );
  }
  removeGenre(index:number)
  {
    console.log('remove genre index ',index);
    this.genres.removeAt(index);
  }
}
