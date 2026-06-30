import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TemplateFragmentDemo } from './template-fragment-demo';

describe('TemplateFragmentDemo', () => {
  let component: TemplateFragmentDemo;
  let fixture: ComponentFixture<TemplateFragmentDemo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TemplateFragmentDemo],
    }).compileComponents();

    fixture = TestBed.createComponent(TemplateFragmentDemo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
