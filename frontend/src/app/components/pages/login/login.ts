import { Component, inject, signal } from '@angular/core';

import { AuthService } from '../../../services/auth-service';
import { Router } from '@angular/router';
import { Actor } from '../../../models/actor.model';
import { form, FormField, FormRoot, required } from '@angular/forms/signals';
import { AuthUser } from '../../../models/auth-user.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormRoot, FormsModule, FormField],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  loginModel = signal<AuthUser>({
    username: '',
    password: '',
    email: '',
    roles: [],
  });
  loginForm = form(this.loginModel, (schemaPath) => {
    required(schemaPath.username, { message: 'Username is required' });
    required(schemaPath.password, { message: 'Password is required' });

  });
  loginService = inject(AuthService);
  router = inject(Router);
  login() {
    //this.loginService.login();
    this.router.navigateByUrl('/');
  }
  onSubmit() {
    let authUser = this.loginModel();
    console.log('formData ', authUser);

    this.loginService.login(authUser,()=>{
      console.log('Successfully logged in');
      this.router.navigateByUrl('/');
    });
  }
}
