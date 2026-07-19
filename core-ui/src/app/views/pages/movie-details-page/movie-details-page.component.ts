import { ChangeDetectorRef, Component, inject, signal } from '@angular/core';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  ColComponent,
  ColDirective,
  FormControlDirective, FormDirective,
  FormFeedbackComponent,
  FormLabelDirective, FormSelectDirective,
  RowComponent, TableModule, UtilitiesModule
} from '@coreui/angular';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from '../../../shared/models/movie.model';
import { MovieService } from '../../../features/movie/services/movie.service';
import { ActorUIComponent } from '../../../shared/component/actor-ui/actor-ui.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AssignedActor } from '../../../shared/models/assigned-actor.model';
import { form, FormField, FormRoot, required } from '@angular/forms/signals';
import { ActorService } from '../../../features/actor/services/actor.service';
import { CommonModule } from '@angular/common';

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
    CommonModule
  ],
  templateUrl: './movie-details-page.component.html',
  styleUrl: './movie-details-page.component.scss',
})
export class MovieDetailsPageComponent {
  private activatedRoute = inject(ActivatedRoute);
  router = inject(Router);
  movieService = inject(MovieService);
  actorService = inject(ActorService);

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
}
