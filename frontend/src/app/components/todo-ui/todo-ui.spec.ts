import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoUI } from './todo-ui';

describe('TodoUI', () => {
  let component: TodoUI;
  let fixture: ComponentFixture<TodoUI>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodoUI],
    }).compileComponents();

    fixture = TestBed.createComponent(TodoUI);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
