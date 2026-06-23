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
  todos: Todo[] = [
    {
      id: 1,
      title: 'Task 1',
    },
    {
      id: 2,
      title: 'Task 2',
    },
    {
      id: 3,
      title: 'Task 3',
    },
  ];

  deleteTodo(todo:Todo) {
    console.log('Delete todo in parent ',todo);
    this.todos = this.todos.filter(td=>td.id!== todo.id);
  }
}
