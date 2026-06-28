import { Component, input } from '@angular/core';
import { CurrencyPipe, DatePipe, TitleCasePipe,UpperCasePipe } from '@angular/common';

@Component({
  selector: 'app-pipe-demo',
  imports: [CurrencyPipe, DatePipe, TitleCasePipe, UpperCasePipe],
  templateUrl: './pipe-demo.html',
  styleUrl: './pipe-demo.css',
})
export class PipeDemo {
  message = input<string>();
  date = new Date();
}
