interface Person {
  readonly name: string;
  age: number;
  address?:string;
}
 
function greet(person: Person) {
  //person.name = "Something";
  return "Hello " + person.name;
}
console.log(greet({
  name : 'Jhon',
  age : 30,
  address : 'Hello'
}));

interface Home {
  readonly resident: { 
    readonly name: string; age: number };
}

let house: Home = {
  resident: { name: "Jhon", age: 30 }
}
/*
house.resident = {

}*/
//house.resident.name = "Update";
house.resident.age = 20;
console.log('house ',house);