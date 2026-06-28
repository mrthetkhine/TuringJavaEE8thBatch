import { Component, input } from '@angular/core';
import { Admin } from '../admin/admin';
import { User } from '../user/user';
import { NgComponentOutlet } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  imports: [NgComponentOutlet],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  user = input.required<string>();

  getBioComponent() {
    return this.user()=='admin' ? Admin : User;
  }
}
