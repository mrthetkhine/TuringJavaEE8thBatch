import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackDemo } from './track-demo';

describe('TrackDemo', () => {
  let component: TrackDemo;
  let fixture: ComponentFixture<TrackDemo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrackDemo],
    }).compileComponents();

    fixture = TestBed.createComponent(TrackDemo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
