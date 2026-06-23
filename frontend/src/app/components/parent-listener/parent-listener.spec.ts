import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentListener } from './parent-listener';

describe('ParentListener', () => {
  let component: ParentListener;
  let fixture: ComponentFixture<ParentListener>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ParentListener],
    }).compileComponents();

    fixture = TestBed.createComponent(ParentListener);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
