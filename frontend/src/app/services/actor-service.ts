import { computed, inject, Service, signal } from '@angular/core';
import { Actor } from '../models/actor.model';
import { HttpClient } from '@angular/common/http';
import { Todo } from '../models/todo.model';
import { ApiResponse } from '../models/api-response.model';
export interface ActorState {
  actors: Actor[];
  loading: boolean;

}
const BASE_URL = 'http://localhost:8080/api';
@Service()
export class ActorService {
  private http = inject(HttpClient);

  private state = signal<ActorState>({
    actors: [],
    loading: false
  });
  actorToEdit: Actor | undefined;

  public actors = computed(() => this.state().actors);
  public loading = computed(() => this.state().loading);
  public actorCount = computed(() => this.state().actors.length);

  constructor() {
    this.loadAllActors();
  }

  loadAllActors() {
    this.http.get<ApiResponse<Actor[]>>(BASE_URL+'/actors')
    .subscribe(response => {
      //console.log('load all actors ', response);
      this.initActors(response.data);
    });
  }
  initActors(actors: Actor[]) {
    this.state.update((currentState) => ({
      loading:false,
      actors: actors
    }));
  }
  addActor(actor: Actor) {
    this.state.update((currentState) => ({
      ...currentState,
      actors: [...currentState.actors, actor]
    }));
  }
  removeActor(actor:Actor)
  {
    this.state.update((currentState) => ({
      ...currentState,
      actors: currentState.actors.filter(act=>act?.id!==actor.id)
    }));
  }
  updateActor(actor:Actor)
  {
    this.state.update((currentState) => ({
      ...currentState,
      actors: currentState.actors.map(act=>act?.id==actor.id?actor:act)
    }));
  }
  apiSaveActor(actor: Actor) {
    this.http.post<ApiResponse<Actor>>(BASE_URL+'/actors',JSON.stringify(actor),
      {headers: { 'Content-Type': 'application/json' }})
      .subscribe(response => {
        console.log('save actor ', response);
        this.addActor(response.data);
      });
  }
  apiDeleteActor(actor:Actor)
  {
    this.http.delete<ApiResponse<Actor>>(BASE_URL+`/actors/${actor.id}`,
      {headers: { 'Content-Type': 'application/json' }})
      .subscribe(response => {
        console.log('delete actor ', response);
        this.removeActor(response.data);
      });
  }
  apiUpdateActor(actor: Actor) {
    this.http.put<ApiResponse<Actor>>(BASE_URL+'/actors/'+actor?.id,JSON.stringify(actor),
      {headers: { 'Content-Type': 'application/json' }})
      .subscribe(response => {
        console.log('save actor ', response);
        this.updateActor(response.data);
      });
  }
}
