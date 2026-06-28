import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StandAloneCounter } from './stand-alone-counter';

describe('StandAloneCounter', () => {
  let component: StandAloneCounter;
  let fixture: ComponentFixture<StandAloneCounter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StandAloneCounter],
    }).compileComponents();

    fixture = TestBed.createComponent(StandAloneCounter);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
