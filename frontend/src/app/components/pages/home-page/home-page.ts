import { Component } from '@angular/core';
import { TemplateDrivenFormDemo } from '../../forms/template-driven-form-demo/template-driven-form-demo';
import { ReactiveForm } from '../../forms/reactive-form/reactive-form';
import { SignalFormDemo } from '../../forms/signal-form-demo/signal-form-demo';

@Component({
  selector: 'app-home-page',
  imports: [TemplateDrivenFormDemo, ReactiveForm, SignalFormDemo],
  templateUrl: './home-page.html',
  styleUrl: './home-page.css',
})
export class HomePage {}
