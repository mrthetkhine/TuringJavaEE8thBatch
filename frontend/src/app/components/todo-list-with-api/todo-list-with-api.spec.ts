import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoListWithApi } from './todo-list-with-api';

describe('TodoListWithApi', () => {
  let component: TodoListWithApi;
  let fixture: ComponentFixture<TodoListWithApi>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodoListWithApi],
    }).compileComponents();

    fixture = TestBed.createComponent(TodoListWithApi);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
