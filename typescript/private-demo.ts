class Base {
  private x = 100;
  #secret= 100;
}
const b = new Base();
console.log('b.x ',b['x']);
//console.log('b.#secret ',b.#secret);

class A {
  private x = 10;
 
  public sameAs(other: A) {
    // No error
    return other.x === this.x;
  }
}
let a = new A();
let c = new A();
console.log('a.sameAs(b) ',a.sameAs(c));