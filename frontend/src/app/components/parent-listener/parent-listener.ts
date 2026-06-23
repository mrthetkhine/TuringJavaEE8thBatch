import { Component } from '@angular/core';
import { ChildOutput } from '../child-output/child-output';

@Component({
  selector: 'app-parent-listener',
  imports: [ChildOutput],
  templateUrl: './parent-listener.html',
  styleUrl: './parent-listener.css',
})
export class ParentListener {

  onChangeEvent(data:number)
  {
    console.log('Data from child',data);
  }
}
