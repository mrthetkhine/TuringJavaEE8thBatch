import { inject, Service } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Todo } from '../models/todo.model';

const URL = 'https://jsonplaceholder.typicode.com/todos';
@Service()
export class TodoService {
  private http = inject(HttpClient);

  loadAllTodo()
  {
    console.log('TodoService loadAllTodo');
    return this.http.get<Todo[]>(URL);
  }
}
