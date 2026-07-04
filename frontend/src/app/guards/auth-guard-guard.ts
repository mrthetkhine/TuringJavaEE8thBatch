import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth-service';

export const authGuardGuard: CanActivateFn = (route, state) => {
  console.log('AuthGuardGuard', route);
  const authService = inject(AuthService);
  const router = inject(Router);
  if(!authService.isAuthenticated())
  {
    router.navigate(['/login']);
    return true;
  }
  else {
    return true;
  }
};
