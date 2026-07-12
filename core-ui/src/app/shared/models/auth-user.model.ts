import { Role } from './role.model';

export interface AuthUser {
  username:string;
  password:string;
  email:string;
  roles:Role[];
}
