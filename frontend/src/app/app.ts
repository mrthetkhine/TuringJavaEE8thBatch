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
import { ParentListener } from './components/parent-listener/parent-listener';
import { Bordered } from './components/bordered/bordered';
import { Layout } from './components/layout/layout';
import { Body } from './components/body/body';
import { Header } from './components/header/header';
import { ViewChildDemo } from './components/view-child/view-child-demo/view-child-demo';
import { TabDemo } from './components/view-child/tab-demo/tab-demo';
import { DomApiDemo } from './components/dom-api-demo/dom-api-demo';
import { Dashboard } from './components/outletdemo/dashboard/dashboard';
import { Binding } from './components/template/binding/binding';
import { TwoWayBinding } from './components/template/two-way-binding/two-way-binding';
import { TrackDemo } from './components/template/track-demo/track-demo';
import { SwitchDemo } from './components/template/switch-demo/switch-demo';
import { PipeDemo } from './components/template/pipe-demo/pipe-demo';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    HelloWorld,
    Greet,
    TodoUI,
    Counter,
    GreetTwo,
    ListItems,
    TodoListDemo,
    ParentListener,
    Bordered,
    Layout,
    Header,
    Body,
    ViewChildDemo,
    TabDemo,
    DomApiDemo,
    Dashboard,
    Binding,
    TwoWayBinding,
    TrackDemo,
    SwitchDemo,
    PipeDemo,
  ],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('frontend');
  message1 = 'Hello World!';

  counter = 10;
  hello = 'This is field hello inside app';
  todo: Todo = {
    title: 'Todo 1',
    completed: true,
  };
  increment() {
    this.counter++;
  }
}
