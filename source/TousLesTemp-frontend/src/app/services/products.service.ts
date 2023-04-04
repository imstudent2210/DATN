import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';
import { Product } from '../share/product.module';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http:HttpClient) { }

  getProductsPaging(page:number = 0, size:number = 5):Observable<any>{
    return this.http.get(`${environment.apiUrl}/product/paging?page=` + page.toString()+`&size=` + size.toString());
  }

  getProducts():Observable<any>{
    return this.http.get(`${environment.apiUrl}/product/get`);
  }

  createProduct(product:any):Observable<any>{
    return this.http.post(`${environment.apiUrl}/product/create2`, product);
  }

  createProduct2(product:FormData){
    return this.http.post<Product>(`${environment.apiUrl}/product/create2`, product);
  }


  // getStoresByName(name:string):Observable<any>{
  //   return this.http.get(`${environment.apiUrl}/store/search/${name}`);
  // }

  // getStoresByAddress(name:string):Observable<any>{
  //   return this.http.get(`${environment.apiUrl}/store/filter/${name}`);
  // }
}
