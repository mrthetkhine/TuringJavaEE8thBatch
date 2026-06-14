type Coord ={
    x:number,
    y:number,
    z?:number
}
function printCoord(coord:Coord)
{
    console.log('x ',coord.x, ' y ',coord.y ,' z ',coord.z);
}
printCoord({
    x: 100,
    y : 200,
    //z : 300
})
type Coord2 = {
    k : number
}