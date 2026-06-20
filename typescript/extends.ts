class Animal {
  move() {
    console.log("Moving along!");
  }
}
 
class Dog extends Animal {
  move() {
      console.log('Dog running');
  }  
  woof(times: number) {
    for (let i = 0; i < times; i++) {
      console.log("woof!");
    }
  }
}
 
const d = new Dog();
// Base class method
d.move();
// Derived class method
d.woof(3)