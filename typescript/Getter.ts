class Item
{
    name:string;
    price:number;
    quantity : number;

    constructor(name:string,price:number,quantity:number)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    get total()
    {
        console.log('getter ');
        return this.price * this.quantity;
    }
}
let item =new Item('Apple',10,2);
console.log('Item ',item.total);