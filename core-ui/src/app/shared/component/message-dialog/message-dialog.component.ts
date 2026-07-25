import { Component, input } from '@angular/core';
import {
  ButtonDirective,
  ModalBodyComponent,
  ModalComponent,
  ModalFooterComponent,
  ModalHeaderComponent
} from '@coreui/angular';

@Component({
  selector: 'app-message-dialog',
  imports: [
    ButtonDirective,
    ModalBodyComponent,
    ModalComponent,
    ModalFooterComponent,
    ModalHeaderComponent
  ],
  templateUrl: './message-dialog.component.html',
  styleUrl: './message-dialog.component.scss',
})
export class MessageDialogComponent {
  title = input.required<string>();
  public visible = false;

  successCallback?:()=>void;
  show()
  {
    this.visible = true;
    console.log('Message show');
  }

  close()
  {
    this.visible = false;
  }
}
