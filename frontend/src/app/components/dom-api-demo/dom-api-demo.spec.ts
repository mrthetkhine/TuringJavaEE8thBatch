import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DomApiDemo } from './dom-api-demo';

describe('DomApiDemo', () => {
  let component: DomApiDemo;
  let fixture: ComponentFixture<DomApiDemo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DomApiDemo],
    }).compileComponents();

    fixture = TestBed.createComponent(DomApiDemo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
