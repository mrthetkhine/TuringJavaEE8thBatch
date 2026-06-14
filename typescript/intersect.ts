type Animal = {
  name: string;
}

type Bear = Animal & { 
  honey: boolean;
}

const bear:Bear = {
  name:'Yogi',
  honey:true
}
console.log('bear ',bear);
        