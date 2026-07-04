import { Service } from '@angular/core';

@Service()
export class AuthService {
  isLoggedIn: boolean = false;

  isAuthenticated()
  {
    return this.isLoggedIn;
  }
  login()
  {
    this.isLoggedIn = true;
  }
}
