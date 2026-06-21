import { Component, input } from '@angular/core';
import { Todo } from '../../models/todo.model';
import { TodoUI } from '../todo-ui/todo-ui';

@Component({
  selector: 'app-todo-list-ui',
  imports: [TodoUI],
  templateUrl: './todo-list-ui.html',
  styleUrl: './todo-list-ui.css',
})
export class TodoListUI {
  todos = input.required<Todo[]>();

  deleteTodo(todo:Todo) {
    console.log('Delete todo in parent ',todo);
  }
}
