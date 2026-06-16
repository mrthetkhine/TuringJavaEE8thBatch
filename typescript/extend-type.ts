interface TwoD
{
    x:number,
    y:number
}
interface ThreeD extends TwoD
{
    z :number;
}
type ThreeD2 = TwoD & {
    z : number
}
type ColoredTwoD = {
    color:string 
} & TwoD
let data: ThreeD2 = {
    x : 100,
    y : 200,
    z : 300
}
console.log('Data ',data);