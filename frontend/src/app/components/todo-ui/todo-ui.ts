import { Component, input, output } from '@angular/core';
import { Todo } from '../../models/todo.model';

@Component({
  selector: 'app-todo-ui',
  imports: [],
  templateUrl: './todo-ui.html',
  styleUrl: './todo-ui.css',
})
export class TodoUI {
  todo = input.required<Todo>({});
  onDeleteTodo = output<Todo>();

  deleteTodo()
  {
    console.log('delete todo child',this.todo());
    this.onDeleteTodo.emit(this.todo());
  }
}
