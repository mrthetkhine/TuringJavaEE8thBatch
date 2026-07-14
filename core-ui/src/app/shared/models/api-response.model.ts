export interface ApiResponse<T> {
  code?:string;
  message:string;
  error?:object;
  data:T;
}
