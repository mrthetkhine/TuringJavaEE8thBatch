let arr  = [1,2,3];//number[]
let tuple : [number,string] = [1,'Hello'];
let data : [string,number,boolean] = ['hello',1,false];
console.log('data ',data[0].toUpperCase());

type MayBe<T> = T | undefined 
function div(a:number , b:number):[MayBe<number>,MayBe<Error> ]{
    if(b==0)
    {
        return [undefined,new Error('Divide by zero')];
    }else{
        return [a/b,undefined];
    }
}

let [result,err] = div(10,0);
if(err)
{
    console.log('err ',err.message);
}else
{
    console.log('result ',result);
}