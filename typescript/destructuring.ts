let obj = {
    name1 : "Jhon",
    age : 30
}
/*
let name = obj.name;
let age = obj.age;
*/
let {name1,age} = obj;
console.log('Name ',name1);
console.log('Age ',age);

function printObj({name1,age}:
                    {name1:string,age:number})
{
    console.log('Name ',name1);
    console.log('Age ',age);
}
printObj(obj);