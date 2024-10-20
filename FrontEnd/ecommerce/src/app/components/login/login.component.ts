import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { User } from '../../models/user.model';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm: FormGroup;
  errorMessage!: string;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private loginService: LoginService, private router: Router) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      chain: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.loginForm.valid) {
      const formData = this.loginForm.value;
      this.login(formData.email, formData.chain)
    }
  }

  login(email: string, chain:string){
    this.userService.setUser(email,chain,'');

    this.loginService.login(this.userService.getUser()).subscribe({
      next: data => {
        if(data.status === 'OK'){
          const userRole = data.role[0];
          localStorage.setItem("token", data.token);

          this.userService.setUser(email,chain,userRole);
          
          if(userRole === 'ADMIN'){
            this.router.navigate(['/manage-users']);
          }else if(userRole === 'GENERAL'){
            this.router.navigate(['/home']);
          }
        }else{
          this.errorMessage = data.status;
        }
      }
    })
  }
}