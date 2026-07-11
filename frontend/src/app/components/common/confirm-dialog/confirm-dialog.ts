import { Component, inject, signal, TemplateRef, viewChild } from '@angular/core';
import { FormRoot } from '@angular/forms/signals';
import { FormsModule } from '@angular/forms';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap/modal';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-confirm-dialog',
  imports: [FormRoot, FormsModule],
  templateUrl: './confirm-dialog.html',
  styleUrl: './confirm-dialog.css',
})
export class ConfirmDialog {
  readonly closeResult = signal('');
  private readonly modalService = inject(NgbModal);
  dialogContent = viewChild<TemplateRef<any>>('content');
  modalRef?: NgbModalRef;
  dialogTitle:string= '';
  successCallback? :()=>void;

  openConfirm(title:string,callback:()=>void)
  {
    this.dialogTitle = title;
    this.modalRef = this.modalService.open(this.dialogContent(), {
      ariaLabelledBy: 'modal-basic-title',

    });
    this.successCallback = callback;
  }
  confirmClick()
  {
    this.successCallback?.();
    this.modalRef?.close();
  }
  closeDialog()
  {
    console.log('Close dialog');
    this.modalRef?.close();
  }
}
