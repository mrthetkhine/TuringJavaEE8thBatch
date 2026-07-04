import { Routes } from '@angular/router';
import { HomePage } from './components/pages/home-page/home-page';
import { TodoListWithApi } from './components/todo-list-with-api/todo-list-with-api';
import { MoviesList } from './components/pages/movies-list/movies-list';
import { MoviesDetails } from './components/pages/movies-details/movies-details';
import { NotFound } from './components/pages/not-found/not-found';
import { Login } from './components/pages/login/login';
import { authGuardGuard } from './guards/auth-guard-guard';

export const routes: Routes = [
  {
    path: '',
    component: HomePage,
  },
 /* {
    path: 'todos',
    component: TodoListWithApi,
  },*/
  {
    path: 'movies',
    component: MoviesList,
    title: 'Movies',
    canActivate: [authGuardGuard],
  },
  {
    path: 'movies/:id',
    component: MoviesDetails,
    canActivate: [authGuardGuard],
  },
  {
    path: 'details',
    redirectTo: '/movies',
  },
  {
    path: 'login',
    component: Login
  },
  {
    path: '**',
    component: NotFound
  },
];
