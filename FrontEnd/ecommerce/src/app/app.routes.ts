import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ManageUsersComponent } from './components/manage-users/manage-users.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'manage-users', component: ManageUsersComponent },
    { path: 'home', component: HomeComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' },
];
