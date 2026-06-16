interface StringArray {
  [index: number]: string;
}
let data:StringArray = ["Hello","World"];

data = {
    1:"one",
    2:"Two"
};

console.log('Data ',data);

interface SquareConfig {
  color?: string;
  width?: number;
}
 
let config : SquareConfig = {
    color: 'red',
    width:100
}
console.log('Config ',config.color?.toUpperCase());