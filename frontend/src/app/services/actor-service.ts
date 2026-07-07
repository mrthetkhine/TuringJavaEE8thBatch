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
  public actors = computed(() => this.state().actors);
  public loading = computed(() => this.state().loading);
  public actorCount = computed(() => this.state().actors.length);

  constructor() {
    this.loadAllActors();
  }

  loadAllActors() {
    this.http.get<ApiResponse<Actor[]>>(BASE_URL+'/actors')
    .subscribe(response => {
      console.log('load all actors ', response);
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
  saveActor(actor: Actor) {
    this.http.post<ApiResponse<Actor>>(BASE_URL+'/actors',JSON.stringify(actor),
      {headers: { 'Content-Type': 'application/json' }})
      .subscribe(response => {
        console.log('save actor ', response);
        this.addActor(response.data);
      });
  }
}
