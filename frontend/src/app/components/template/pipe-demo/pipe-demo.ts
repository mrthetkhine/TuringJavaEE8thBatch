import { Component, input } from '@angular/core';
import { CurrencyPipe, DatePipe, TitleCasePipe,UpperCasePipe } from '@angular/common';
import { BigCasePipe } from '../../../pipes/big-case-pipe';

@Component({
  selector: 'app-pipe-demo',
  imports: [CurrencyPipe, DatePipe, TitleCasePipe, UpperCasePipe, BigCasePipe],
  templateUrl: './pipe-demo.html',
  styleUrl: './pipe-demo.css',
})
export class PipeDemo {
  message = input<string>();
  date = new Date();
}
