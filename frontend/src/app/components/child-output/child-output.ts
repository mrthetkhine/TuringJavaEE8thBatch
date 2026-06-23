import { Component, Output,EventEmitter } from '@angular/core';


@Component({
  selector: 'app-child-output',
  imports: [],
  templateUrl: './child-output.html',
  styleUrl: './child-output.css',
})
export class ChildOutput {
  @Output() onChange = new EventEmitter<number>();

  counter = 0;
  trigger()
  {
    this.onChange.emit(this.counter);
    this.counter++;
  }
}
