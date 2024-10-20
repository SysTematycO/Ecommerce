import { Injectable } from "@angular/core";
import { User } from "../models/user.model";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private user!: User;

    setUser(email: string, chain: string, role: string): void {
        this.user = new User(email, chain, role);
    }

    getUser(): User {
        return this.user;
    }

    clearUser(): void {
        this.user = new User('','',''); 
    }
}
