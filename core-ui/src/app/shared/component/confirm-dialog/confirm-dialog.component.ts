import { Component, input } from '@angular/core';
import {
  ButtonDirective,
  ModalBodyComponent,
  ModalComponent,
  ModalFooterComponent,
  ModalHeaderComponent
} from '@coreui/angular';

@Component({
  selector: 'app-confirm-dialog',
  imports: [
    ModalComponent,
    ModalHeaderComponent,
    ModalBodyComponent,
    ModalFooterComponent,
    ButtonDirective
  ],
  templateUrl: './confirm-dialog.component.html',
  styleUrl: './confirm-dialog.component.scss',
})
export class ConfirmDialogComponent {
  title = input.required<string>();
  public visible = false;

  successCallback?:()=>void;
  openConfirm(successCallback:() => void)
  {
    this.visible = true;
    console.log('Confirm open');
    this.successCallback = successCallback;
  }
  onSuccessHandle()
  {
    this.visible = false;
    this.successCallback?.();
  }
  close()
  {
    this.visible = false;
  }
}
