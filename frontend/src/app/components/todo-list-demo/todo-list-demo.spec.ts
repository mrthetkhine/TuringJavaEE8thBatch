import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoListDemo } from './todo-list-demo';

describe('TodoListDemo', () => {
  let component: TodoListDemo;
  let fixture: ComponentFixture<TodoListDemo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodoListDemo],
    }).compileComponents();

    fixture = TestBed.createComponent(TodoListDemo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
