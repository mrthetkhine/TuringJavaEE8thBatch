import { Component, inject } from '@angular/core';
import { ActorService } from '../../../services/actor-service';
import { ActorCount } from '../actor-count/actor-count';
import { NewActorForm } from '../new-actor-form/new-actor-form';

@Component({
  selector: 'app-actor-list',
  imports: [ActorCount, NewActorForm],
  templateUrl: './actor-list.html',
  styleUrl: './actor-list.css',
})
export class ActorList {
  protected actorService = inject(ActorService);
}
