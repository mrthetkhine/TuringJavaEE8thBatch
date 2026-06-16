class Human
{
    name:string;
    constructor(name:string)
    {
        console.log('Human constructor ',name);
        this.name = name;
    }
}
class Teacher extends Human
{
    constructor(name:string)
    {
        console.log('Teacher constructor ',name);
        super(name);
    }
}
type SomeConstructor = {
  new (s: string): Human;
};
let con:SomeConstructor = Teacher;
let teacher = new con('Jhon');
console.log('Teacher ',teacher);

con = Human;
let human = new con('Wick');
console.log('Human ',human);