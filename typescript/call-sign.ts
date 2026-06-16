function greet(message:string):void 
{
    console.log('Greet ',message);
}
greet.description = "Greet Function";

function hello(msg:string):void
{
    console.log('hello');
}
type DescribableFunction = {
  description: string;
  (someArg: string): void;
};
let fn :DescribableFunction = greet;
console.log('Description ',fn.description);

//fn = hello;