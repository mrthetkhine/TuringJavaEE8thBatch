const user = {
  id: 123,
 
  admin: false,
  becomeAdmin: function () {
    this.admin = true;
  },
};

let obj :object = {
  name :'Jhon',
  age : 30
}
obj = {
    message : 'Hello'
}
obj = {
  data: 100
}
//obj = "Hello";
console.log('obj ',obj);

let unk:unknown = 10;
unk = {};
unk = false;


unk = {
  message: 'Hello'
};
console.log('unk.message ',unk);

let some :any = false;
some = {
     message: 'Hello'
}
console.log('Some.message ',some.message);