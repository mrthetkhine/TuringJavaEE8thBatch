import { Directive } from '@angular/core';

@Directive({
  selector: '[appRedColor]',
  standalone: true,
  host:{
    '[style.color]': 'color',
  }
})
export class RedColor {
  color='red';
}
