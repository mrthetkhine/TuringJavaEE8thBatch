type Greet = 'hello' ;
let g :Greet = 'hello';

type Comparator = -1 | 0|1;
let result : Comparator = -1;

let something; 
console.log('type of ',typeof something);

let anything = null;
console.log('type of ',typeof anything);

function liveDangerously(x?: number | null) {
  // No error
  console.log(x!.toFixed());
}
liveDangerously(10);