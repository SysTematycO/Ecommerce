import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ManageUserService } from '../../services/manage-user.service';
import { CommonModule } from '@angular/common';
import { User } from '../../models/user.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-users',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, CommonModule],
  templateUrl: './manage-users.component.html',
  styleUrl: './manage-users.component.scss'
})
export class ManageUsersComponent {

  email!: string;
  chain!: string;
  role!: string;

  users!: any[];
  userForm!: FormGroup;

  errorAddUser!: string;

  constructor(private manageUserService: ManageUserService, private formBuilder: FormBuilder, private router: Router){
    this.userForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      chain: ['', [Validators.required]],
      role: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.getAllUser();
  }

  getAllUser(){
    this.manageUserService.getAllUser().subscribe(
      {
        next: data => {
          this.users = data.map((user: any) => ({
            ...user,
            editing: false
          }));
        }
      }
    )
  }

  addUser(){
    if (this.userForm.valid) {
      const formData = this.userForm.value;

      this.manageUserService.addUser(new User(formData.email, formData.chain, formData.role)).subscribe(
        {
          next: data => {
            if(data.status === 'success'){
              this.getAllUser();
              this.errorAddUser = '';
            } else {
              this.errorAddUser = data.message;
            }
          }
        }
      )
    }
  }

  updateUser(user: any){
    this.manageUserService.updateUser(user).subscribe(
      {
        next: data => {
          if(data.status === 'success'){
            this.getAllUser();
          } else {
            this.errorAddUser = data.message;
          }
        }
      }
    )
  }

  editUser(user: any, activate: boolean){
    user.editing = activate;
  }

  deleteUser(user: any){
    this.manageUserService.deleteUser(user.email).subscribe(
      {
        next: data => {
          if(data.status === 'success'){
            this.getAllUser();
          } else {
            this.errorAddUser = data.message;
          }
        }
      }
    )
  }
  
  goHome(){
    this.router.navigate(['/home']);
  }

  logout(){
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
