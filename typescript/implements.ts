interface Pingable {
  data:string;
  ping(): void;
}
 
class Sonar implements Pingable {
  data: string;

  constructor(data:string)
  {
    this.data = data;
  }
  ping() {
    console.log("ping!");
  }
}
/*
class Ball implements Pingable {

  pong() {
    console.log("pong!");
  }
}
*/
let sonar:Pingable = new Sonar('sonar 1');
sonar.ping();