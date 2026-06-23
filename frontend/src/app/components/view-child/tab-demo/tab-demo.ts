import { Component, viewChildren } from '@angular/core';
import { Tab } from '../tab/tab';
import { TabPage } from '../tab-page/tab-page';

@Component({
  selector: 'app-tab-demo',
  imports: [Tab, TabPage],
  templateUrl: './tab-demo.html',
  styleUrl: './tab-demo.css',
})
export class TabDemo {
  pages =viewChildren(TabPage);

  ngOnInit() {

    //console.log('tab demo pages==> ',this.pages());
  }
}
