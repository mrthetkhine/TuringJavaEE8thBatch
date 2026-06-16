function longest<Type extends { length: number }>(a: Type, b: Type) {
  if (a.length >= b.length) {
    return a;
  } else {
    return b;
  }
}

console.log('string ',longest('apple','hi'));
console.log('arr ',longest([1,2,3],[1,2]));
//console.log('arr ',longest(1,3));