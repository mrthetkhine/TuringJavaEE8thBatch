import { Component, inject, signal, viewChild } from '@angular/core';
import {
  ButtonDirective,
  ColComponent,
  ColDirective,
  FormControlDirective,
  FormDirective,
  FormFeedbackComponent,
  FormLabelDirective, FormSelectDirective,
  ModalBodyComponent,
  ModalComponent,
  ModalFooterComponent, ModalHeaderComponent, ModalTitleDirective,
  RowComponent,
  TableDirective
} from '@coreui/angular';
import { MovieUIComponent } from '../../../features/movie/component/movie-ui/movie-ui.component';
import { ActorService } from '../../../features/actor/services/actor.service';
import { Actor } from '../../../shared/models/actor.model';
import { ConfirmDialogComponent } from '../../../shared/component/confirm-dialog/confirm-dialog.component';
import { applyEach, form, FormField, FormRoot, minLength, required } from '@angular/forms/signals';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Movie } from '../../../shared/models/movie.model';
import { AssignedActor } from '../../../shared/models/assigned-actor.model';

@Component({
  selector: 'app-actors-page',
  imports: [
    RowComponent,
    ColComponent,
    MovieUIComponent,
    TableDirective,
    ButtonDirective,
    ConfirmDialogComponent,
    ColDirective,
    FormControlDirective,
    FormDirective,
    FormFeedbackComponent,
    FormLabelDirective,
    FormRoot,
    FormsModule,
    ModalBodyComponent,
    ModalComponent,
    ModalFooterComponent,
    ModalHeaderComponent,
    ModalTitleDirective,
    ReactiveFormsModule,
    FormField,
    FormSelectDirective
  ],
  templateUrl: './actors-page.component.html',
  styleUrl: './actors-page.component.scss',
})
export class ActorsPageComponent {
  public visible = false;
  actorService =inject(ActorService);
  dlgDeleteConfirm = viewChild(ConfirmDialogComponent);
  actorModel = signal<Actor>({
    id:'',
    firstName: '',
    lastName: '',
    gender: '',

  });
  actorForm = form(this.actorModel, (schemaPath) => {
    required(schemaPath.firstName, { message: 'First Name is required' });
    minLength(schemaPath.firstName,3, { message: 'First Name must be at least 3 characters' });
    required(schemaPath.lastName, { message: 'Last Name is required' });
    minLength(schemaPath.lastName,3, { message: 'Last Name must be at least 3 characters' });
    required(schemaPath.gender, { message: 'Gender is required' });

  },{
    submission: {
      action: async (form) => {
        // Handle your API call or submission logic here
        let actor = form().value();
        if(this.editMode)
        {
          this.editActor(actor);
        }
        else
        {
          this.saveActor(actor);
        }

      }
    }
  });

  editMode = false;
  private emptyActor:Actor = {
    id:'',
    firstName:'',
    lastName:'',
    gender:'',

  };


  openNewActorDialog() {
    this.editMode= false;
    this.actorModel.set(this.emptyActor);
    this.actorForm().reset();
    this.openModal();
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

  onEditClick(actor:Actor)
  {
    console.log('Edit ',actor);
    this.editMode = true;
    this.actorModel.set({
      id:actor.id,
      firstName:actor.firstName,
      lastName:actor.lastName,
      gender:actor.gender
    })
    this.openModal();
  }
  onDeleteClick(actor:Actor)
  {

    this.dlgDeleteConfirm()?.openConfirm(()=>{
      console.log('Delete confirm ');
      this.actorService.apiDeleteActor(actor);
    });
  }
  editActor(actor:Actor)
  {
    this.actorService.apiUpdateActor(actor);
    this.closeModal();
  }
  saveActor(actor:Actor)
  {
    this.actorService.apiSaveActor(actor);
    this.closeModal();
  }

}
