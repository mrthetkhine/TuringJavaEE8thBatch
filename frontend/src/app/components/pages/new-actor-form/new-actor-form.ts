import { Component, signal, inject, TemplateRef, input, contentChild, viewChild } from '@angular/core';
import {
  form,
  FormField,
  required,
  email,
  FormRoot,
  min,
  minLength,
  SchemaPathTree,
  applyEach
} from '@angular/forms/signals';
import { Movie } from '../../../models/movie.model';
import { Actor } from '../../../models/actor.model';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap/modal';
import { ActorService } from '../../../services/actor-service';



@Component({
  selector: 'app-new-actor-form',
  imports: [FormField, ReactiveFormsModule, FormRoot, FormsModule],
  templateUrl: './new-actor-form.html',
  styleUrl: './new-actor-form.css',
})
export class NewActorForm {
  private readonly modalService = inject(NgbModal);
  dialogContent = viewChild<TemplateRef<any>>('content');
  actorService = inject(ActorService);
  editMode = false;

  actorModel = signal<Actor>({
    id: '0',
    firstName: '',
    lastName: '',
    gender: '',
  });
  actorForm = form(this.actorModel, (schemaPath) => {
    required(schemaPath.firstName, { message: 'FirstName is required' });
    required(schemaPath.lastName, { message: 'LastName is required' });
    required(schemaPath.gender, { message: 'Gender is required' });
  });

  readonly closeResult = signal('');

  modalRef?: NgbModalRef;

  openNewActorDialog()
  {
    this.editMode = false;
    this.actorForm().reset();
    this.actorModel.set({
      id:undefined,
      firstName:'',
      lastName:'',
      gender:'',
    });
    this.modalRef = this.modalService.open(this.dialogContent(), {
      ariaLabelledBy: 'modal-basic-title',
      size: 'lg'
    });
  }
  openEditActorDialog(actor: Actor)
  {
    console.log('actor to edit ',actor);
    this.editMode = true;

    this.actorModel.set({
      id:actor?.id,
      firstName:actor.firstName,
      lastName:actor.lastName,
      gender:actor.gender,
    });

    this.modalRef = this.modalService.open(this.dialogContent(), {
      ariaLabelledBy: 'modal-basic-title',
      size: 'lg'
    });
  }

  onSubmit() {
    const formData = this.actorModel();
    console.log('Form Data: ', formData);
    if(this.editMode)
    {
      console.log('edit actor ',formData);
      this.actorService.apiUpdateActor(formData);
      this.modalRef?.close();
    }
    else
    {

      // Send to server
      this.actorService.apiSaveActor(formData);
      this.modalRef?.close();
    }

  }
}
