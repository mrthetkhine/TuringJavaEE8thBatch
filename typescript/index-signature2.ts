class BuildInventory {
  // Index signature definition
  [itemName: string]: number; 

  constructor() {
    this.wood = 100; // Allowed
    this.stone = 50;  // Allowed
  }
}
let item = new BuildInventory();
item['data'] = 400;
item.something= 300;
console.log('Item ',item);