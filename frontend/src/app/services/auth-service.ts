import { inject, Service } from '@angular/core';
import { AuthUser } from '../models/auth-user.model';
import { HttpClient } from '@angular/common/http';
import { ApiResponse } from '../models/api-response.model';
import { Actor } from '../models/actor.model';
import { AuthResponse } from '../models/auth-response.model';
const BASE_URL = 'http://localhost:8080/api';
@Service()
export class AuthService {
  private http = inject(HttpClient);
  isLoggedIn: boolean = false;
  authUser?: AuthResponse;

  isAuthenticated()
  {
    return this.isLoggedIn;
  }
  login(authUser:AuthUser,successCallback:()=>void)
  {
    this.http.post<ApiResponse<AuthResponse>>(BASE_URL+'/login',JSON.stringify(authUser),
      {headers: { 'Content-Type': 'application/json' }})
      .subscribe(response => {
        console.log('Login Response ', response);
        if(response.data?.token)
        {
          this.authUser =response.data;
          this.isLoggedIn = true;
          console.log('token successfull');
          successCallback();
        }
        else {
          this.authUser = undefined;
          this.isLoggedIn = false;

        }

      });
  }
}
