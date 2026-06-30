import { Component, inject } from '@angular/core';
import { Logging } from '../../../services/logging';

@Component({
  selector: 'app-variable',
  imports: [],
  templateUrl: './variable.html',
  styleUrl: './variable.css',
})
export class Variable {

  private logger = inject(Logging);

  log()
  {
    this.logger.log('Hello');
  }
}
