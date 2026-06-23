import { ChangeDetectorRef, Component, ContentChildren, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-tab-page',
  imports: [],
  templateUrl: './tab-page.html',
  styleUrl: './tab-page.css',
})
export class TabPage {
  display = false;
  constructor(public cdr: ChangeDetectorRef) {}

  ngOnChanges(changes: SimpleChanges) {
    console.log(changes);
  }
  show()
  {
    console.log('show');
    this.display = true;
    this.cdr.detectChanges();
  }
  hide()
  {
    console.log('hide');
    this.display = false;
    this.cdr.detectChanges();
  }
}
