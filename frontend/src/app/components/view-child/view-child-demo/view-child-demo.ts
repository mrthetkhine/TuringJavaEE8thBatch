import { Component, viewChild } from '@angular/core';
import { Counter } from '../../counter/counter';

@Component({
  selector: 'app-view-child-demo',
  imports: [Counter],
  templateUrl: './view-child-demo.html',
  styleUrl: './view-child-demo.css',
})
export class ViewChildDemo {
  counterComponent = viewChild(Counter);

  inc()
  {
    console.log('ViewChildDemo Inc==>');
    this.counterComponent()?.increment();
    this.counterComponent()?.increment();
  }
}
