import { Component, input } from '@angular/core';

function toUpperCase(value: string|undefined ): string {
  return value?.toUpperCase() ?? '';
}

@Component({
  selector: 'app-greet',
  imports: [],
  templateUrl: './greet.html',
  styleUrl: './greet.css',
})
export class Greet {
  message = input.required({
    alias:'data',
    transform:toUpperCase
  });

}
