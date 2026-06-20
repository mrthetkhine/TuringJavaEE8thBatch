class Box<T>{
    value: T;
    constructor(value:T)
    {
        this.value = value
    }
    getValue = () => {
        console.log('this ',this);
        return this.value;
    };
}
let box:Box<number> = new Box(100);
console.log('Box ',box.value);

let box2 =new Box('Hello');
console.log('box2 ',box2.value.toUpperCase());
console.log('box2 ',box2.getValue());