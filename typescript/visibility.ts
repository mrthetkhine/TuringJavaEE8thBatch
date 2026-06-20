class Member
{
    public name:string;
    constructor(name:string)
    {
        this.name = name;
    }
}
let mem =new Member('test');
console.log('Mem ',mem.name);

class Greeter {
  public greet() {
    console.log("Hello, " + this.getName());
  }
  protected getName() {
    return "hi";
  }
}
 
class SpecialGreeter extends Greeter {
  public howdy() {
    // OK to access protected member here
    console.log("Howdy, " + this.getName());
  }
}
let obj :SpecialGreeter = new SpecialGreeter();
//console.log('getName ',obj.getName());