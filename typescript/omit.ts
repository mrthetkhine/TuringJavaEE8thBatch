interface Todo {
  title: string;
  description: string;
  completed: boolean;
  createdAt: number;
}
 
type TodoPreview = Omit<Todo, "description">;
 
const todo: TodoPreview = {
  title: "Clean room",
  completed: false,
  createdAt: 1615544252770,
};

type T0 = Exclude<"a" | "b" | "c", "a">;
type T1 = Extract<"a" | "b" | "c", "a" | "f">;

type T3 = NonNullable<string | number | undefined | null>;