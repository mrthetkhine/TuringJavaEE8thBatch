import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabDemo } from './tab-demo';

describe('TabDemo', () => {
  let component: TabDemo;
  let fixture: ComponentFixture<TabDemo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TabDemo],
    }).compileComponents();

    fixture = TestBed.createComponent(TabDemo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
