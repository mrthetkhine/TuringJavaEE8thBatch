class Human
{
    name : string;
    constructor(name:string)
    {
        this.name = name;
        console.log('Human constructor');
    }
    display()
    {
        console.log('Name is ',this.name);
    }
}
class Teacher extends Human
{
    constructor(name:string)
    {
        super(name);
        console.log('Teacher constructor');
    }
}
let h: Human = new Teacher('Jhon');
h.display();

let obj = {
    name : "Jhon",
    display: function()
    {
        console.log('My name is ',this.name);
    }
}

let obj2 = {
    name : "Jhon",
    display: function()
    {
        console.log('My name is ',this.name);
    }
}