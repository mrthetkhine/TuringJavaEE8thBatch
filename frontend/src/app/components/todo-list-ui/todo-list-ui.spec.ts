import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoListUI } from './todo-list-ui';

describe('TodoListUI', () => {
  let component: TodoListUI;
  let fixture: ComponentFixture<TodoListUI>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodoListUI],
    }).compileComponents();

    fixture = TestBed.createComponent(TodoListUI);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
