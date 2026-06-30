import { Component,inject } from '@angular/core';
import { LargeComponent } from '../large-component/large-component';
import { RedColor } from '../../../directives/red-color';

import { Logging } from '../../../services/logging';

@Component({
  selector: 'app-defer-demo',
  imports: [LargeComponent, RedColor],
  preserveWhitespaces: true,
  templateUrl: './defer-demo.html',
  styleUrl: './defer-demo.css',
})
export class DeferDemo {
  private logger = inject(Logging);

  log()
  {
    this.logger.log('Hello');
  }
}
