import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewUIComponent } from './review-ui.component';

describe('ReviewUIComponent', () => {
  let component: ReviewUIComponent;
  let fixture: ComponentFixture<ReviewUIComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReviewUIComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReviewUIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
