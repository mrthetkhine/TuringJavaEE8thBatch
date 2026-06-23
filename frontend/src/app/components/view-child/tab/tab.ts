import { Component, contentChildren, input, viewChildren } from '@angular/core';
import { TabPage } from '../tab-page/tab-page';

@Component({
  selector: 'app-tab',
  imports: [],
  templateUrl: './tab.html',
  styleUrl: './tab.css',
})
export class Tab {
  headers =input<string[]>();
  pages =contentChildren(TabPage);

  ngOnInit() {
    console.log('headers ',this.headers());
    console.log('pages ',this.pages());
    this.pages()[0].show();
  }
  clickHeader(index: number) {
    console.log('clickHeader',index);
    const pages = this.pages();
    for(let i = 0; i < pages.length; i++) {
      console.log('hide ',i);
      const page = pages[i];
      page.hide();

    }
    console.log('show ',pages[index]);
    pages[index].show();

    /*this.pages().forEach(child => {
      // Access the child's own injected ChangeDetectorRef instance
      child.cdr.detectChanges();
    });*/
  }
}
