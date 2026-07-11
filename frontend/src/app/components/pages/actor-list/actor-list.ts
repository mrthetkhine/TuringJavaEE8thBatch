import { Component, inject, viewChild } from '@angular/core';
import { ActorService } from '../../../services/actor-service';
import { ActorCount } from '../actor-count/actor-count';
import { NewActorForm } from '../new-actor-form/new-actor-form';
import { Actor } from '../../../models/actor.model';
import { ConfirmDialog } from '../../common/confirm-dialog/confirm-dialog';

@Component({
  selector: 'app-actor-list',
  imports: [ActorCount, NewActorForm, ConfirmDialog],
  templateUrl: './actor-list.html',
  styleUrl: './actor-list.css',
})
export class ActorList {
  actorForm = viewChild(NewActorForm);
  confirmDialog= viewChild(ConfirmDialog);
  protected actorService = inject(ActorService);

  showDeleteConfirm(actor: Actor) {
    this.confirmDialog()?.openConfirm('Are you sure you want to delete?',()=>{
      this.actorService.apiDeleteActor(actor);
    });
    //
  }
  showNewActorDialog() {
    console.log('show new actor dialog');
    this.actorForm()?.openNewActorDialog();
  }
  showUpdateActorForm(actor: Actor) {
    console.log('update actor', actor);
    this.actorForm()?.openEditActorDialog(actor);
  }
}
