import { computed, inject, Injectable, signal } from '@angular/core';
import { Review } from '../../../shared/models/review.model';
import { HttpClient } from '@angular/common/http';
import { MovieState } from './movie.service';
import { ApiResponse } from '../../../shared/models/api-response.model';
import { BASE_URL } from '../../../shared/util/Config';
import { Movie } from '../../../shared/models/movie.model';

export interface ReviewState {
  reviews: Review[];
  loading: boolean;
}
@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  private http = inject(HttpClient);
  private state = signal<ReviewState>({
    reviews: [],
    loading: false
  });
  public reviews = computed(() => this.state().reviews);

  apiLoadAllReviewsByMovieId(movieId:string) {
    this.http.get<ApiResponse<Review[]>>(BASE_URL+`/movies/${movieId}/reviews`)
      .subscribe(response => {
        this.initReviews(response.data);
      });
  }
  initReviews(reviews: Review[]) {
    this.state.update((currentState) => ({
      loading:false,
      reviews: reviews
    }));
  }
  apiSaveReview(review: Review) {

    this.http.post<ApiResponse<Review>>(BASE_URL+`/movies/${review.movieId}/reviews`, JSON.stringify(review),
      {headers: { 'Content-Type': 'application/json' }})
      .subscribe(response => {
        this.saveReview(response.data);
      });
  }
  saveReview(review: Review) {
    this.state.update((currentState) => ({
      ...currentState,
      reviews: [...currentState.reviews,review]
    }));
  }
  apiUpdateReview(review: Review) {

    this.http.put<ApiResponse<Review>>(BASE_URL+`/movies/${review.movieId}/reviews/${review.id}`, JSON.stringify(review),
      {headers: { 'Content-Type': 'application/json' }})
      .subscribe(response => {
        this.updateReview(response.data);
      });
  }
  updateReview(review: Review) {
    this.state.update((currentState) => ({
      ...currentState,
      reviews: currentState.reviews.map(r=>r.id==review.id?review:r)
    }));
  }
}
