import { Component } from '@angular/core';
import { StandAloneCounter } from '../stand-alone-counter/stand-alone-counter';

@Component({
  selector: 'app-track-demo',
  imports: [StandAloneCounter],
  templateUrl: './track-demo.html',
  styleUrl: './track-demo.css',
})
export class TrackDemo {
  items = ['one', 'two', 'three'];
  //items = [];

  reverse()
  {
    this.items.reverse();
  }
}
