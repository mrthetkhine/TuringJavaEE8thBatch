interface Pair<T,S>
{
    first : T;
    second : S;
}
let data : Pair<string,number> = {
    first : "Hello",
    second : 100
}
console.log('Data ',data.first.toUpperCase(), ' second ',data.second.toFixed());

let arr:Array<number> = [1,2,3];
console.log('Arr ',arr);

let arr2:ReadonlyArray<number> =[1,2,3,4];
//arr2[0] = 100;

let item:any = arr2;
item[0] = 100;
console.log('Arr2 ',arr2);