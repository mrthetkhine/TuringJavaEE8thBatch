class MyClass {
  static x = 10;
  y = 100;

  static{
    console.log('Static block 1 ',MyClass.x );
  }
  static{
    console.log('Static block 2');
  }

  static printX() {
    console.log(MyClass.x);
    //console.log('y ',this.y);
  }
  display()
  {
      console.log('x ',MyClass.x);
      console.log('y ',this.y);
  }
}
console.log(MyClass.x);
MyClass.printX();

let obj = new MyClass();
obj.display();
console.log('MyClass.x ',MyClass.x);