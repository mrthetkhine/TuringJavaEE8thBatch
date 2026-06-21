import { Component, computed, input, model } from '@angular/core';

@Component({
  selector: 'app-counter',
  imports: [],
  templateUrl: './counter.html',
  styleUrl: './counter.css',
})
export class Counter {
  counter =model(0);
  doubledCounter = computed(() => this.counter() * 2);

  increment()
  {
    this.counter.update(c=>c+1);
  }
}
