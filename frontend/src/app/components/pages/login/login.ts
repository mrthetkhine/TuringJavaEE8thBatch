import { Component, inject, signal } from '@angular/core';

import { AuthService } from '../../../services/auth-service';
import { ActivatedRoute, Router } from '@angular/router';
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
  authService = inject(AuthService);
  router = inject(Router);
  private route = inject(ActivatedRoute);
  redirectUrl:string |null = null;

  ngOnInit() {

    this.route.queryParamMap.subscribe(params => {
      this.redirectUrl = params.get('redirectUrl');
      console.log('redirectUrl ',this.redirectUrl);
    });
  }

  login() {
    //this.loginService.login();
    this.router.navigateByUrl('/');
  }
  onSubmit() {
    let authUser = this.loginModel();
    console.log('formData ', authUser);

    this.authService.login(authUser,()=>{
      console.log('Successfully logged in');
      if(this.redirectUrl)
      {
        this.router.navigateByUrl(this.redirectUrl);
      }
      else {
        this.router.navigateByUrl('/');
      }

    },()=>{
      alert('Invalid username or password');
    });
  }
}
