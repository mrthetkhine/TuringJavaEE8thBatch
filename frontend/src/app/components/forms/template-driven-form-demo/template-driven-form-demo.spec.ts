import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TemplateDrivenFormDemo } from './template-driven-form-demo';

describe('TemplateDrivenFormDemo', () => {
  let component: TemplateDrivenFormDemo;
  let fixture: ComponentFixture<TemplateDrivenFormDemo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TemplateDrivenFormDemo],
    }).compileComponents();

    fixture = TestBed.createComponent(TemplateDrivenFormDemo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
