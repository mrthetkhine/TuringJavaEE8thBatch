import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Bordered } from './bordered';

describe('Bordered', () => {
  let component: Bordered;
  let fixture: ComponentFixture<Bordered>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Bordered],
    }).compileComponents();

    fixture = TestBed.createComponent(Bordered);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
