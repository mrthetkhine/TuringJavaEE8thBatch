type RGB = 'red'| 'green' | 'blue';
type Color = {
    color : RGB,
}
interface Circle  {
    radius: number
}
type ColoredCircle = Color & Circle;

let c : ColoredCircle = {
    color : 'red',
    radius : 100
}