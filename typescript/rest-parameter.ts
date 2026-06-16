function sum(...args:number[])
{
    let total = 0;
    for(let i=0;i<args.length;i++)
    {
        total += args[i];
    }
    return total;
}
console.log('sum ',sum(1));
console.log('sum 1,2',sum(1,2));

const arr1 = [1, 2, 3];
const arr2 = [4, 5, 6];
arr1.push(...arr2); //arr1.push(4,5,6);

console.log('arr1 ',arr1);