import { Component } from '@angular/core';
import { TemplateDrivenFormDemo } from '../../forms/template-driven-form-demo/template-driven-form-demo';

@Component({
  selector: 'app-home-page',
  imports: [TemplateDrivenFormDemo],
  templateUrl: './home-page.html',
  styleUrl: './home-page.css',
})
export class HomePage {}
