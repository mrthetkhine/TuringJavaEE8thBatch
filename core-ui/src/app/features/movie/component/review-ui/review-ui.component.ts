import { Component, input, output, SimpleChanges, ViewChild } from '@angular/core';
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
import { NgxStarsComponent, NgxStarsModule } from 'ngx-stars';

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
  @ViewChild(NgxStarsComponent)
  starsComponent?: NgxStarsComponent;
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
  ngOnChanges(changes: SimpleChanges): void {
    console.log('Review changes ',changes, ' reivew ',this.review());
    this.starsComponent?.setRating(this.review().rating);
  }
}
