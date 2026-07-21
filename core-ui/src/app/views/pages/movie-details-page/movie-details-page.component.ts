import { ChangeDetectorRef, Component, inject, signal, ViewChild } from '@angular/core';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  ColComponent,
  ColDirective,
  FormControlDirective,
  FormDirective,
  FormFeedbackComponent,
  FormLabelDirective,
  FormSelectDirective,
  ModalBodyComponent,
  ModalComponent,
  ModalFooterComponent,
  ModalHeaderComponent, ModalTitleDirective,
  RowComponent,
  TableModule,
  UtilitiesModule
} from '@coreui/angular';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from '../../../shared/models/movie.model';
import { MovieService } from '../../../features/movie/services/movie.service';
import { ActorUIComponent } from '../../../shared/component/actor-ui/actor-ui.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AssignedActor } from '../../../shared/models/assigned-actor.model';
import { form, FormField, FormRoot, min, minLength, required } from '@angular/forms/signals';
import { ActorService } from '../../../features/actor/services/actor.service';
import { CommonModule } from '@angular/common';
import { ReviewService } from '../../../features/movie/services/review.service';
import { ReviewUIComponent } from '../../../features/movie/component/review-ui/review-ui.component';
import { Review } from '../../../shared/models/review.model';
import { Actor } from '../../../shared/models/actor.model';
import { NgxStarsComponent, NgxStarsModule } from 'ngx-stars';

@Component({
  selector: 'app-movie-details-page',
  imports: [
    RowComponent,
    ColComponent,
    ColDirective,
    FormControlDirective,
    FormFeedbackComponent,
    FormLabelDirective,
    CardComponent,
    CardBodyComponent,
    ActorUIComponent,
    ButtonDirective,
    FormSelectDirective,
    ReactiveFormsModule,
    FormField,
    FormDirective,
    FormRoot,
    FormsModule,
    TableModule,
    UtilitiesModule,
    CommonModule,
    ReviewUIComponent,
    ModalBodyComponent,
    ModalComponent,
    ModalFooterComponent,
    ModalHeaderComponent,
    ModalTitleDirective,
    NgxStarsModule
  ],
  templateUrl: './movie-details-page.component.html',
  styleUrl: './movie-details-page.component.scss',
})
export class MovieDetailsPageComponent {
  private activatedRoute = inject(ActivatedRoute);
  router = inject(Router);

  movieService = inject(MovieService);
  actorService = inject(ActorService);
  reviewService = inject(ReviewService);

  movieId?: string;
  movie?:Movie;
  assignedActorModel =signal<AssignedActor>({
    id:'',
  });
  assignedActorForm = form(this.assignedActorModel, (schemaPath) => {
    required(schemaPath.id, { message: 'Actor  is required' });

  },{
    submission: {
      action: async (form) => {
        // Handle your API call or submission logic here
        let assignedActor = form().value();
        this.assignActorToMovie(assignedActor);

      }
    }
  });
  public visible = false;
  reviewModel = signal<Review>({
    id:'',
    review: '',
    rating: 0,
    movieId:'',
  });
  emptyReview = {
    id:'',
    review: '',
    rating: 0,
    movieId:'',
  }
  reviewForm = form(this.reviewModel, (schemaPath) => {
    required(schemaPath.review, { message: 'Review is required' });
    required(schemaPath.rating, { message: 'Rating is required' });
    min(schemaPath.rating, 1,{ message: 'Rating must be at least 1' });
  },{
    submission: {
      action: async (form) => {
        // Handle your API call or submission logic here
        let review = form().value();
        if(this.editMode)
        {
          this.editReview(review);
        }
        else
        {
          this.saveReview(review);
        }
      }
    }
  });
  editMode = false;
  changeRating = false;
  rating = 0;
  @ViewChild(NgxStarsComponent)
  starsComponent?: NgxStarsComponent;

  constructor(private cdr: ChangeDetectorRef) {
    this.movieId = this.activatedRoute.snapshot.paramMap.get('id') ?? '';
    console.log('Movie Details Page', this.movieId);

    console.log('Movie Details movie', this.movie);
  }
  ngOnInit() {
    this.movieService.getMovieById(this.movieId!)
      .subscribe(response=>{
        console.log('movie details page', response);
        this.movie = response.data;
        this.cdr.detectChanges();
      });
    this.reviewService.apiLoadAllReviewsByMovieId(this.movieId!);
  }
  handleBack()
  {
    this.router.navigate(['/movies']);
  }
  assignActorToMovie(assignedActor:AssignedActor)
  {
    console.log('assign to movie ',this.movie?.id);
    console.log('Assigned actor to movie ',assignedActor);
    this.movieService.apiAssignActorToMovie(this.movieId!,assignedActor.id).subscribe(response=>{
      this.movie = response.data;
      this.cdr.detectChanges();
    });
  }
  handleLiveDemoChange(event: any) {
    this.visible = event;
  }

  openModal()
  {
    this.visible = true;
  }
  closeModal()
  {
    this.visible = false;
  }
  onRatingSet(rating:number)
  {
    this.changeRating = true;
    this.rating = rating;
    this.starsComponent?.setRating(this.rating);
    this.reviewModel.update(model=>({
      ...model,
        rating:rating
    }));
  }
  showNewReviewDialog()
  {
    console.log('ShowNewReviewDialog');
    this.editMode= false;
    this.changeRating = false;
    this.reviewModel.set(this.emptyReview);
    this.starsComponent?.setRating(this.emptyReview.rating);
    this.reviewForm().reset();
    this.openModal();
  }
  onEditHandler(review:Review)
  {
    console.log('edit review ',review);
    this.editMode = true;
    this.changeRating = false;
    this.rating = review.rating;
    this.starsComponent?.setRating(this.rating);
    this.reviewModel.set({
      ...review
    });
    console.log('edit review model ',this.reviewModel());
    this.openModal();
    //this.cdr.detectChanges();
  }
  onDeleteHandler(review:Review)
  {
    console.log('edit review ',review);
  }
  editReview(review:Review)
  {
    console.log('edit review ',review);
    this.reviewService.apiUpdateReview(review);
    this.closeModal();
  }
  saveReview(review:Review)
  {
    delete review.id;
    review.movieId = this.movieId!;
    console.log('save review ',review);
    this.reviewService.apiSaveReview(review);
    this.closeModal();
  }
}
