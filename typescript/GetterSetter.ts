class Person
{
    name :string;
    _age:number

    constructor(name:string,age:number)
    {
        this.name = name;
        this._age = age;
    }
    set age(newAge:number)
    {
        console.log('setter ',newAge);
        if(newAge>0 && newAge >120)
        {
            this._age = newAge;
        }
        else
        {
            this._age = newAge;
        }
    }
    display()
    {
        console.log('Name is ',this.name);
        console.log('Age is ',this._age);
    }
}
let p = new Person('Jhon',30);
p.display();
console.log('Before setter');
p.age = 200;
p.display();

p.age = 100;
p.display();