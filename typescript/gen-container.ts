interface Box<T> {
    value: T;
}
let box: Box<string> = {
    value: "Hello"
}
let box2 : Box<number> = {
    value : 100
}

interface Human{
    name : string;
    display:()=>void;
}
let h: Human = {
    name: "Jhon",
    display()
    {
        console.log('My name is ',this.name)
    }
};
h.display();