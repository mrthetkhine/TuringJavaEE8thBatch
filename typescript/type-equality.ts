class Point1 {
  x = 0;
  y = 0;
}
 
class Point2 {
  x = 0;
  //y :boolean = false;
  y = 10;
}

let p:Point1 = new Point2();
console.log('P ',p);

class Person {
  name: string = "Person";
  age: number = 10;
}
 
class Employee {
  name: string ="Jhon";
  age: number = 34;
  salary: number =200;
}
 
let p1: Person = new Employee();
console.log('P1 ',p1);