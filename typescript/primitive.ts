//let a = "Hello";//
let a: string = "Hello";
console.log('typeof a ',typeof a);

let flag:boolean = false;
console.log('typeof flag ',typeof flag);

let b:number = 200;
console.log('typeof b ',typeof b);

let arr = [1,2,3];
console.log('typeof arr ',typeof arr);

let arr2:Array<number> = [2,3,4,5];
console.log('arr2 ', arr2);

let arr3 : number[] = [4,5,6];
console.log('Arr3 ',arr3);

let data :any = 100;
console.log('data ',data);
data = "Hello";
console.log('data ',data);

data = {
    name: "Jhon"
};
console.log('data ',data);