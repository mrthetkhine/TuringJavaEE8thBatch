//function
//any parameter

function toDouble(x:number):number
{
    return x*2;
}
function getLength(message:string):number
{
    return message.length;
}

type Fun<T,S> = (t:T)=>S; 

let fun:Fun<number,number> = toDouble;
let len:Fun<string,number> = getLength;

console.log('fun ',fun(10));
console.log('len ',len('Hello'));