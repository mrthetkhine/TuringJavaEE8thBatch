import { Component,inject } from '@angular/core';

import { AuthService } from '../../../services/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  loginService = inject(AuthService);
  router=inject(Router);
  login()
  {
    this.loginService.login();
    this.router.navigateByUrl('/');
  }
}
