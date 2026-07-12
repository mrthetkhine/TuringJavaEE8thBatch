import { Component, signal } from '@angular/core';
import { IconDirective } from '@coreui/icons-angular';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  CardGroupComponent,
  ColComponent,
  ContainerComponent,
  FormControlDirective,
  FormDirective, FormFeedbackComponent,
  InputGroupComponent,
  InputGroupTextDirective,
  RowComponent
} from '@coreui/angular';
import { AuthUser } from '../../../shared/models/auth-user.model';
import { form, FormField, FormRoot, required } from '@angular/forms/signals';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [ContainerComponent, RowComponent, ColComponent, CardGroupComponent, CardComponent, CardBodyComponent, FormDirective, InputGroupComponent, InputGroupTextDirective, IconDirective, FormControlDirective, ButtonDirective, FormRoot, FormsModule, FormField, FormFeedbackComponent]
})
export class LoginComponent {
  loginModel = signal<AuthUser>({
    username: '',
    password: '',
    email: '',
    roles: [],
  });
  loginForm = form(this.loginModel, (schemaPath) => {
    required(schemaPath.username, { message: 'Username is required' });
    required(schemaPath.password, { message: 'Password is required' });

  });
  onSubmit() {
    let authUser = this.loginModel();
    console.log('formData ', authUser);


  }
}
