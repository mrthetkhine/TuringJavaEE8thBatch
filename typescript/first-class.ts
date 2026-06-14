let fun = function()//Rule1
{
    console.log('Hello');
}
fun();

function accept(fn:any)//Rule2
{
    console.log('inside accept');
    fn();
}

accept(fun);

function get()//Rule 3
{
    console.log('inside get');
    return fun;
    
}
get()();