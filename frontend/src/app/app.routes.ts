import { Routes } from '@angular/router';
import { HomePage } from './components/pages/home-page/home-page';
import { TodoListWithApi } from './components/todo-list-with-api/todo-list-with-api';
import { MoviesList } from './components/pages/movies-list/movies-list';
import { MoviesDetails } from './components/pages/movies-details/movies-details';
import { NotFound } from './components/pages/not-found/not-found';
import { Login } from './components/pages/login/login';
import { authGuardGuard } from './guards/auth-guard-guard';
import { ActorList } from './components/pages/actor-list/actor-list';
import { ActorCount } from './components/pages/actor-count/actor-count';
import { Logout } from './components/pages/logout/logout';

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
    path: 'actors',
    component: ActorList,
    canActivate: [authGuardGuard],
  },
  {
    path: 'actors-count',
    component: ActorCount,
  },
  {
    path: 'login',
    component: Login
  },
  {
    path: 'logout',
    canActivate: [authGuardGuard],
    component: Logout
  },
  {
    path: '**',
    component: NotFound
  },
];
