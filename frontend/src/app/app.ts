import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HelloWorld } from './components/hello-world/hello-world';
import { Greet } from './components/greet/greet';
import { Todo } from './models/todo.model';
import { TodoUI } from './components/todo-ui/todo-ui';
import { Counter } from './components/counter/counter';
import { GreetTwo } from './components/greet-two/greet-two';
import { ListItems } from './components/list-items/list-items';
import { TodoListDemo } from './components/todo-list-demo/todo-list-demo';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HelloWorld, Greet, TodoUI, Counter, GreetTwo, ListItems, TodoListDemo],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('frontend');
  message1 = 'Hello World!';

  counter = 10;
  todo: Todo = {
    title: 'Todo 1',
    completed: true,
  };
  increment() {
    this.counter++;
  }
}
