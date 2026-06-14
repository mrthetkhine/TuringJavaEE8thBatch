function greet(message:string):void
{
    console.log('Greet ',message);
}
greet('hello');
//greet(123);

function add(a:number,b:number):number
{
    return a+b;
}
let result = add(10,20);
console.log('Result ',result);

const names = ["Alice", "Bob", "Eve"];
names.forEach(name => console.log(name.toUpperCase()));