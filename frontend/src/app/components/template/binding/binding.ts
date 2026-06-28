import { Component, HostListener, input, signal, SimpleChanges } from '@angular/core';
import { HelloWorld } from '../../hello-world/hello-world';

@Component({
  selector: 'app-binding',
  imports: [HelloWorld],
  templateUrl: './binding.html',
  styleUrl: './binding.css',
  host: {
    'body:click': 'onWindowClick($event)',
  },
})
export class Binding {
  value = input<string>();
  theme = 'dark';
  role = 'button';
  background = false;

  //container = ['container','active'];
  container = signal({
    container: true,
    active: false,
  });
  mode = 'inline-block';
  ngDoCheck() {
    console.log('ngDoCheck');
  }
  ngOnChanges(changes: SimpleChanges) {
    console.log(changes);
  }
  toggle() {
    this.background = !this.background;
  }
  onClick(event: MouseEvent) {
    console.log('click', event);
  }
  @HostListener('document:click', ['$event'])
  onWindowClick(event: MouseEvent) {
    console.log('onWindowClick', event);
  }
  onRightClick(event: MouseEvent) {
    console.log('onRightClick', event);
    event.preventDefault();
  }
}
