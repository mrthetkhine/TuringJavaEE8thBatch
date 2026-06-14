interface Point{
    x : number,
    y : number
}

let data:Point = {
    x : 100,
    y : 200,
    z : 300
}
console.log('Data ',data);

interface Point {
    z: number
}

interface Animal {
  name: string;
}

interface Bear extends Animal {
  honey: boolean;
}

const bear:Bear = {
  name: "Yogi",
  honey: true
};
console.log('bear ',bear);