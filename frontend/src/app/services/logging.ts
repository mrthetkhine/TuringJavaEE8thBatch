import { Service } from '@angular/core';

@Service()
export class Logging {
  constructor() {
    console.log('logging constructor');
  }
  log(message:string)
  {
    console.log('Logger ',message);
  }
}
