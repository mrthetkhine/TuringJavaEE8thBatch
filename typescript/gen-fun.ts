function firstElement(arr:number[])
{
    return arr[0];
}
console.log('firstElement ',firstElement([1,2,3,4,5]));

function firstElementV2(arr:any[])
{
    return arr[0];
}
//console.log('firstElementV2 ',firstElementV2([1,"2",3,4,5]).toUpperCase());

function firstElementV3<T>(arr:T[])
{
    return arr[0];
}
console.log('firstElementV3 ',firstElementV3<string>(["apple","orange"]).toUpperCase());
console.log('firstElementV3 ',firstElementV3([1,2,3]).toFixed());