import { Component, inject, signal, viewChild } from '@angular/core';
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
import { AuthService } from '../../../shared/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmDialogComponent } from '../../../shared/component/confirm-dialog/confirm-dialog.component';
import { MessageDialogComponent } from '../../../shared/component/message-dialog/message-dialog.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [
    ContainerComponent,
    RowComponent,
    ColComponent,
    CardGroupComponent,
    CardComponent,
    CardBodyComponent,
    FormDirective,
    InputGroupComponent,
    InputGroupTextDirective,
    IconDirective,
    FormControlDirective,
    ButtonDirective,
    FormRoot,
    FormsModule,
    FormField,
    FormFeedbackComponent,
    MessageDialogComponent
  ]
})
export class LoginComponent {
  messageDlg = viewChild(MessageDialogComponent);

  authService = inject(AuthService);
  router = inject(Router);
  private route = inject(ActivatedRoute);
  redirectUrl:string |null = null;

  loginModel = signal<AuthUser>({
    username: '',
    password: '',
    email: '',
    roles: [],
  });
  loginForm = form(this.loginModel, (schemaPath) => {
    required(schemaPath.username, { message: 'Username is required' });
    required(schemaPath.password, { message: 'Password is required' });

  },{
    submission: {
      action: async (form) => {
        // Handle your API call or submission logic here
        let authUser = form().value();
        console.log('Submitting data...', authUser);
        this.authService.login(authUser,()=>{
          console.log('Successfully logged in');
          if(this.redirectUrl)
          {
            this.router.navigateByUrl(this.redirectUrl);
          }
          else {
            this.router.navigateByUrl('/');
          }

        },()=>{
          this.messageDlg()?.show();
        });
      }
    }
  });
  ngOnInit() {

    this.route.queryParamMap.subscribe(params => {
      this.redirectUrl = params.get('redirectUrl');
      console.log('redirectUrl ',this.redirectUrl);
    });
  }
}
