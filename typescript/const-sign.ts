class Human
{
    constructor()
    {
        console.log('Human contructor');
    }
}
class Teacher extends Human
{
    constructor()
    {
        console.log('Teacher contructor');
        super();
    }
}
class Doctor extends Human
{
    constructor()
    {
        console.log('Doctor contructor');
        super();
    }
}
type ctor = typeof Human;
let con:ctor = Teacher;
let obj = new con();
console.log('obj ',obj);

con = Doctor;
obj = new con();
console.log('obj ',obj);