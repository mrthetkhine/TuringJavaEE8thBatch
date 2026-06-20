interface Todo {
  title: string;
  description: string;
}
type PartialTodo = Partial<Todo>;
let todo : PartialTodo = {
    title : "Learn Typescript"
}
interface Props {
  a?: number;
  b?: string;
}
const obj2: Required<Props> = { a: 5 ,b:"100"};

type ReadonlyTodo = Readonly<Todo>;
let todo2 : ReadonlyTodo = {
    title : "Learn Typescript",
    description : "Learn Typescript"
}
//todo2.description = "Learn Typescript";