import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Product } from '../models/product.model';
import { BuyProduct } from '../models/buyProduct.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

    constructor(private http: HttpClient) {}

    getAllProduct(): Observable<any> {
        return this.http.get(`${environment.backend}${environment.getAllProducts}`)
    }

    getAllProductsPopular(): Observable<any> {
      return this.http.get(`${environment.backend}${environment.getAllProductsPopular}`)
   }

    addProduct(product: Product): Observable<any> {
      return this.http.post(`${environment.backend}${environment.addProduct}`, product)
    }

    buyProduct(buyProduct: BuyProduct): Observable<any>{
      return this.http.put(`${environment.backend}${environment.buyProduct}`, buyProduct)
    }

    deleteProduct(idProduct: string): Observable<any> {
      return this.http.delete(`${environment.backend}${environment.deleteProduct}/${idProduct}`)
    }
}
