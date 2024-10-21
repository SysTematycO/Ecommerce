import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ManageUserService {

    constructor(private http: HttpClient) {}

    getAllUser(): Observable<any> {
        return this.http.get(`${environment.backend}${environment.getAllUser}`)
    }

    addUser(user: User): Observable<any> {
      return this.http.post(`${environment.backend}${environment.addUser}`, user)
    }

    updateUser(user: User): Observable<any> {
      return this.http.put(`${environment.backend}${environment.updateUser}`, user)
    }

    deleteUser(email: string): Observable<any> {
      return this.http.delete(`${environment.backend}${environment.deleteUser}/${email}`)
    }
}
