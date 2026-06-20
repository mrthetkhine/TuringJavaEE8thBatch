type CatName = "miffy" | "boris" | "mordred";
 
interface CatInfo {
  age: number;
  breed: string;
}
const cats: Record<CatName, CatInfo> = {
  miffy: { age: 10, breed: "Persian" },
  boris: { age: 5, breed: "Maine Coon" },
  mordred: { age: 16, breed: "British Shorthair" },
  //another : {age : 10,breed : "Persian"}
};
interface Todo {
   title: string;
   description: string;
   completed: boolean;
}
type TodoPreview = Pick<Todo, "title" | "completed">;
let todo : TodoPreview = {
    title : "Learn Typescript",
    completed : true
}