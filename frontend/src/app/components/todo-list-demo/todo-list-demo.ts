import { Component } from '@angular/core';
import { Todo } from '../../models/todo.model';
import { ListItems } from '../list-items/list-items';
import { TodoListUI } from '../todo-list-ui/todo-list-ui';

@Component({
  selector: 'app-todo-list-demo',
  imports: [ListItems, TodoListUI],
  templateUrl: './todo-list-demo.html',
  styleUrl: './todo-list-demo.css',
})
export class TodoListDemo {
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
      id: 1,
      title: 'Task 3',
    },
  ];
}
