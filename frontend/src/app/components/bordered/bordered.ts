import { Component, HostListener } from '@angular/core';
import { Key } from 'node:readline';

@Component({
  selector: 'app-bordered',
  imports: [],
  templateUrl: './bordered.html',
  styleUrl: './bordered.css',
  host: {
   /* '(click)': 'updateValue($event)',*/
  },
})
export class Bordered {
  updateValue(event: MouseEvent) {
    console.log('Click Event ',event);
  }
  @HostListener('click', ['$event'])
  updateValue2(event: MouseEvent) {
    console.log('Keydown ',event);
  }
}
