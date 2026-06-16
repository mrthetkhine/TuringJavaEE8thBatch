let fun: Function ;
fun = ()=>{
    console.log('Fun');
}
fun();

fun = (message:string)=>{
    console.log('Fun ',message);
}
fun('hello');