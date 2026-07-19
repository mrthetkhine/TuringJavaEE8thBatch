import { computed, inject, Injectable, signal } from '@angular/core';
import { Actor } from '../../../shared/models/actor.model';
import { HttpClient } from '@angular/common/http';
import { MovieState } from '../../movie/services/movie.service';
import { ApiResponse } from '../../../shared/models/api-response.model';
import { Movie } from '../../../shared/models/movie.model';
import { BASE_URL } from '../../../shared/util/Config';

export interface ActorState {
  actors: Actor[];
  loading: boolean;
}
@Injectable({
  providedIn: 'root',
})
export class ActorService {
  private http = inject(HttpClient);
  private state = signal<ActorState>({
    actors: [],
    loading: false
  });
  public actors = computed(() => this.state().actors);
  constructor() {
    this.apiLoadAllActors();
  }

  apiLoadAllActors() {
    this.http.get<ApiResponse<Actor[]>>(BASE_URL+'/actors')
      .subscribe(response => {
        this.initActors(response.data);
      });
  }
  initActors(actors: Actor[]) {
    this.state.update((currentState) => ({
      loading:false,
      actors: actors
    }));
  }
  apiSaveActor(actor: Actor) {
    delete actor.id;
    this.http.post<ApiResponse<Actor>>(BASE_URL+'/actors', JSON.stringify(actor),
      {headers: { 'Content-Type': 'application/json' }})
      .subscribe(response => {
        this.addActor(response.data);
      });
  }
  addActor(actor: Actor) {
    this.state.update((currentState) => ({
      ...currentState,
      actors: [...currentState.actors,actor]
    }));
  }
  apiUpdateActor(actor: Actor) {

    this.http.put<ApiResponse<Actor>>(BASE_URL+`/actors/${actor.id}`, JSON.stringify(actor),
      {headers: { 'Content-Type': 'application/json' }})
      .subscribe(response => {
        this.updateActor(response.data);
      });
  }
  updateActor(actor: Actor) {
    this.state.update((currentState) => ({
      ...currentState,
      actors: currentState.actors.map(a=>a.id==actor.id?actor:a)
    }));
  }
  apiDeleteActor(actor: Actor) {
    this.http.delete<ApiResponse<Actor>>(BASE_URL+`/actors/${actor.id}`)
      .subscribe(response => {
        this.deleteActor(response.data);
      });
  }
  deleteActor(actor: Actor) {
    this.state.update((currentState) => ({
      ...currentState,
      actors: currentState.actors.filter(a => actor.id !== a.id)
    }));
  }
}
