import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'bigCase',
})
export class BigCasePipe implements PipeTransform {
  transform(value?: string) {
    return value?.toUpperCase();
  }
}
