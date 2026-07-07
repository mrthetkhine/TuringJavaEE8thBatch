import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewActorForm } from './new-actor-form';

describe('NewActorForm', () => {
  let component: NewActorForm;
  let fixture: ComponentFixture<NewActorForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewActorForm],
    }).compileComponents();

    fixture = TestBed.createComponent(NewActorForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
