import { Component, TemplateRef, viewChild } from '@angular/core';
import { CommonModule, NgComponentOutlet, NgTemplateOutlet } from '@angular/common';
import { User } from '../../outletdemo/user/user';
import { Admin } from '../../outletdemo/admin/admin';

@Component({
  selector: 'app-template-fragment-demo',
  imports: [NgTemplateOutlet, NgComponentOutlet,CommonModule],
  templateUrl: './template-fragment-demo.html',
  styleUrl: './template-fragment-demo.css',
})
export class TemplateFragmentDemo {
  templateRef = viewChild<TemplateRef<unknown>>('myFragment');

  user = "admin";
  adminTemplate = viewChild('admin', {read: TemplateRef});
  basicTemplate = viewChild('basic', {read: TemplateRef});
  items = [
    {
      title : 'Title 1',
      description : 'Description 1',
    },
    {
      title : 'Title 2',
      description : 'Description 2',
    },
  ]
  constructor() {
    console.log('TemplateFragmentDemo constructor ', this.templateRef);
  }

  profileComponent() {
    return this.user === "admin" ? Admin : User;
  }

  profileTemplate()
  {
    return this.user==='admin'? this.adminTemplate() : this.basicTemplate();
  }
}
