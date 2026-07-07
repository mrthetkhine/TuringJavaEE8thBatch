import { Component, signal, inject, TemplateRef } from '@angular/core';
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
  actorService = inject(ActorService);
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
  private readonly modalService = inject(NgbModal);
  readonly closeResult = signal('');

  modalRef?:NgbModalRef ;

  open(content: TemplateRef<any>) {
    this.modalRef = this.modalService.open(content, {
      ariaLabelledBy: 'modal-basic-title',
      size: 'lg'
    });

  }

  onSubmit() {
    const formData = this.actorModel();
    console.log('Form Data: ', formData);
    // Send to server
    this.actorService.saveActor(formData);
    this.modalRef?.close();
  }
}
