import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OffersService {

    constructor(private http: HttpClient) {}

    getOffers(): Observable<any> {
        return this.http.get(`${environment.backend}${environment.getOffers}`)
    }
}