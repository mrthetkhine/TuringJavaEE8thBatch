//alignment = 'center' 'left' right
type Alignment =   'center' | 'left' | 'right';
let alignment : Alignment = 'center';
//alignment = 'another';

type ID = number | string;
let id : ID = 100;

//console.log('id ',id.toUpperCase());
id = '100';
console.log('id ',id.toUpperCase());

function printId(id: ID) {
  if (typeof id === "string") {
    // In this branch, id is of type 'string'
    console.log(id.toUpperCase());
  } else {
    // Here, id is of type 'number'
    console.log(id);
  }
}
printId('112233Ad');
printId(100);