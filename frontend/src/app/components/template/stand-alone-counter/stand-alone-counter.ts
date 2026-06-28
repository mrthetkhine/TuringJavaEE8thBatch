import { Component, input } from '@angular/core';

@Component({
  selector: 'app-stand-alone-counter',
  imports: [],
  templateUrl: './stand-alone-counter.html',
  styleUrl: './stand-alone-counter.css',
})
export class StandAloneCounter {
    counter = 0;
    label = input<string>();
    inc()
    {
      this.counter++;
    }
}
