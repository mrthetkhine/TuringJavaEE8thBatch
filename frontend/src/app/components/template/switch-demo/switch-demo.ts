import { Component } from '@angular/core';

@Component({
  selector: 'app-switch-demo',
  imports: [],
  templateUrl: './switch-demo.html',
  styleUrl: './switch-demo.css',
})
export class SwitchDemo {
  counter = 0;

  inc()
  {
    this.counter += 1;
  }
}
