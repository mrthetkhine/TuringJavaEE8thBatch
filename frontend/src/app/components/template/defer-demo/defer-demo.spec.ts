import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeferDemo } from './defer-demo';

describe('DeferDemo', () => {
  let component: DeferDemo;
  let fixture: ComponentFixture<DeferDemo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeferDemo],
    }).compileComponents();

    fixture = TestBed.createComponent(DeferDemo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
