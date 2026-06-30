import { Component, inject } from '@angular/core';
import { TodoService } from '../../services/todo-service';
import { Todo } from '../../models/todo.model';
import { TodoUI } from '../todo-ui/todo-ui';

@Component({
  selector: 'app-todo-list-with-api',
  imports: [TodoUI],
  templateUrl: './todo-list-with-api.html',
  styleUrl: './todo-list-with-api.css',
})
export class TodoListWithApi {
  todos: Todo[] = [];
  todoService = inject(TodoService);

  ngOnInit() {
    console.log('TodoListWithApi ngOnInit');
    this.todoService.loadAllTodo()
                    .subscribe(todos => {
                      this.todos = todos;
                    });
  }
  deleteTodo(todo:Todo) {

  }
}
