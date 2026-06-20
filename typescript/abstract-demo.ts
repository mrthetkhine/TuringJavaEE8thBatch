abstract class Base {
  abstract getName(): string;
 
  printName() {
    console.log("Hello, " + this.getName());
  }
}
 
class Derived extends Base {
    constructor(){
        console.log('Derived 1constructor');
        super();
    }
  getName() {
    return "World";
  }
}
class Derived2 extends Base {
    constructor(){
        console.log('Derived 1constructor');
        super();
    }
  getName() {
    return "World";
  }
}
const b = new Derived();
b.printName();

type ctor = typeof Derived;
let con: ctor = Derived;
let derived = new con();
derived.printName();