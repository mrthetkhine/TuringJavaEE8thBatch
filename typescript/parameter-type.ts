declare function f1(arg: { a: number; b: string }): number;
type T0 = Parameters<() => string>;
type T1 = Parameters<(a: number) => void>;
type T2 = Parameters<typeof f1>;


type T3 = ReturnType<typeof f1>;