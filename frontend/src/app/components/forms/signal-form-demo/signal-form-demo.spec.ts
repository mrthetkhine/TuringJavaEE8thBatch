import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignalFormDemo } from './signal-form-demo';

describe('SignalFormDemo', () => {
  let component: SignalFormDemo;
  let fixture: ComponentFixture<SignalFormDemo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SignalFormDemo],
    }).compileComponents();

    fixture = TestBed.createComponent(SignalFormDemo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
