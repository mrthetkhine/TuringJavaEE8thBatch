import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabPage } from './tab-page';

describe('TabPage', () => {
  let component: TabPage;
  let fixture: ComponentFixture<TabPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TabPage],
    }).compileComponents();

    fixture = TestBed.createComponent(TabPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
