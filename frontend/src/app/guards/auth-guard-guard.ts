import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth-service';

export const authGuardGuard: CanActivateFn = (route, state) => {
  //console.log('AuthGuardGuard', route);
  const authService = inject(AuthService);
  const router = inject(Router);
  const targetUrl = state.url;
  console.log('Navigating to:', targetUrl);
  //console.log('isAuthenticated:', authService.isAuthenticated());
  if(!authService.isAuthenticated())
  {
    router.navigate([`/login`], {
      queryParams: {
        redirectUrl:targetUrl
      }
    });
    return true;
  }
  else {
    return true;
  }
};
