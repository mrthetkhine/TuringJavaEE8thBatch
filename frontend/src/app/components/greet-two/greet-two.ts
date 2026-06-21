import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-greet-two',
  imports: [],
  templateUrl: './greet-two.html',
  styleUrl: './greet-two.css',
})
export class GreetTwo {
  @Input() message: string='';
}
