import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewChildDemo } from './view-child-demo';

describe('ViewChildDemo', () => {
  let component: ViewChildDemo;
  let fixture: ComponentFixture<ViewChildDemo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewChildDemo],
    }).compileComponents();

    fixture = TestBed.createComponent(ViewChildDemo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
