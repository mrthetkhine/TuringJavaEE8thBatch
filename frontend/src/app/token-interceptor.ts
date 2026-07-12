import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './services/auth-service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const publicRoutes = ['/api/login'];
  const authService = inject(AuthService);
  const isPublicRoute = publicRoutes.some(route => req.url.includes(route));
  const authToken = authService.authUser?.token;

  if (authToken && !isPublicRoute) {
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${authToken}`
      }
    });
    return next(authReq);
  }
  else
  {
    return next(req);
  }

};
