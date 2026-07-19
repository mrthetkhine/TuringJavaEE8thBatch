import { Component, input } from '@angular/core';
import { Actor } from '../../models/actor.model';
import {
  CardBodyComponent,
  CardComponent,
  ColComponent,
  ColDirective,
  FormControlDirective, FormLabelDirective,
  RowComponent
} from '@coreui/angular';

@Component({
  selector: 'app-actor-ui',
  imports: [
    CardBodyComponent,
    CardComponent,
    RowComponent,
    ColComponent,
    ColDirective,
    FormControlDirective,
    FormLabelDirective
  ],
  templateUrl: './actor-ui.component.html',
  styleUrl: './actor-ui.component.scss',
})
export class ActorUIComponent {
  actor =input.required<Actor>();
}
