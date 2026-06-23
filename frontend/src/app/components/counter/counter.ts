import { Component, computed, input, model, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-counter',
  imports: [],
  templateUrl: './counter.html',
  styleUrl: './counter.css',
})
export class Counter {
  counter =model(0);
  doubledCounter = computed(() => this.counter() * 2);

  message ='';

  constructor() {
    console.log('Counter constructor');
  }
  ngOnInit()
  {
    console.log('Counter ngOnInit');
  }
  ngOnChanges(changes: SimpleChanges): void {
    console.log('Counter Changes ',changes);
    this.message= 'Now counter is '+this.counter();
  }
  ngDoCheck()
  {
    console.log('Counter ngDoCheck');
  }
  ngAfterContentInit()
  {
    console.log('Counter ngAfterContentInit');
  }
  ngAfterContentChecked()
  {
    console.log('Counter ngAfterContentChecked');
  }
  ngAfterViewInit()
  {
    console.log('Counter ngAfterViewInit');
  }
  afterNextRender()
  {
    console.log('Counter ngAfterNextRender');
  }
  afterEveryRender()
  {
    console.log('Counter ngAfterEveryRender');
  }
  increment()
  {
    this.counter.update(c=>c+1);
  }
}
