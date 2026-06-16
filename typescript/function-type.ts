function add(a:number,b:number):number
{
    return a+b;
}
function subtract(a:number,b:number):number
{
    return a-b;
}
type BinaryFn = (a:number,b:number)=>number;
let fn:BinaryFn = add;

console.log('add ',fn(10,20));
fn= subtract;

console.log('subtract ',fn(10,20));

function greet(message:string):void 
{
    console.log('Greet ',message);
}
type Callback = (msg:string)=>void;
let callback:Callback = greet;
callback('Hello');