import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GreetTwo } from './greet-two';

describe('GreetTwo', () => {
  let component: GreetTwo;
  let fixture: ComponentFixture<GreetTwo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GreetTwo],
    }).compileComponents();

    fixture = TestBed.createComponent(GreetTwo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
