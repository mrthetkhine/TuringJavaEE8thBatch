import { Component, inject } from '@angular/core';
import { ActorService } from '../../../services/actor-service';

@Component({
  selector: 'app-actor-count',
  imports: [],
  templateUrl: './actor-count.html',
  styleUrl: './actor-count.css',
})
export class ActorCount {
  protected actorService = inject(ActorService);
}
