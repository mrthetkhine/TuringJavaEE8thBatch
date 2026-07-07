import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActorCount } from './actor-count';

describe('ActorCount', () => {
  let component: ActorCount;
  let fixture: ComponentFixture<ActorCount>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActorCount],
    }).compileComponents();

    fixture = TestBed.createComponent(ActorCount);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
