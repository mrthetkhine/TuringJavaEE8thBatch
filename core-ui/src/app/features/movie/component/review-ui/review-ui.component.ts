import { Component, input, output } from '@angular/core';
import { Review } from '../../../../shared/models/review.model';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  ColComponent,
  ColDirective,
  FormControlDirective, FormLabelDirective,
  RowComponent
} from '@coreui/angular';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgxStarsModule } from 'ngx-stars';

@Component({
  selector: 'app-review-ui',
  imports: [
    CardComponent,
    CardBodyComponent,
    RowComponent,
    ColComponent,
    NgxStarsModule,
    ButtonDirective
  ],
  templateUrl: './review-ui.component.html',
  styleUrl: './review-ui.component.scss',
})
export class ReviewUIComponent {
  review = input.required<Review>();

  onEdit = output<Review>();
  onDelete = output<Review>();

  onRatingSet(rating: number){
    console.log('rating set ',rating);
  }
  editHandler()
  {
    this.onEdit.emit(this.review());
  }
  deleteHandler()
  {
    this.onDelete.emit(this.review());
  }
}
