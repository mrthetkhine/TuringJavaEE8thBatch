import { Component, ElementRef, Injector } from '@angular/core';
import { inject } from '@angular/core/primitives/di';

@Component({
  selector: 'app-dom-api-demo',
  imports: [],
  templateUrl: './dom-api-demo.html',
  styleUrl: './dom-api-demo.css',
})
export class DomApiDemo {

  elementRef:ElementRef<any>;

  constructor(injector: Injector) {
    this.elementRef = injector.get(ElementRef);
  }
  ngOnInit() {
    //console.log('DomApiDemo ele ref ',this.elementRef);
  }
  focus()
  {
    console.log('Focus');
    this.elementRef.nativeElement.querySelector('input')?.focus();
  }
}
