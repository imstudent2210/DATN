import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment.prod';
import { Product } from '../model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http:HttpClient) { }

  getProductsPaging(page:number = 0, size:number = 5):Observable<any>{
    return this.http.get(`${environment.API_BASE_URL}/product/paging?page=` + page.toString()+`&size=` + size.toString());
  }

  getProducts():Observable<any>{
    return this.http.get(`${environment.API_BASE_URL}/product/get2`);
  }
  getProductById(id:any){
    return this.http.get<Product>(`${environment.API_BASE_URL}/product/get2/${id}`);
  }

  // 1 usage
  createProduct(product:FormData):Observable<any>{
    return this.http.post<Product>(`${environment.API_BASE_URL}/product/create2`, product);
  }
  updateProduct2(product:FormData, id:any):Observable<any>{
    return this.http.put<Product>(`${environment.API_BASE_URL}/product/update2/${id}`, product);
  }

  deleteProduct(id:any){
    return this.http.delete(`${environment.API_BASE_URL}/product/delete2/${id}`);
  }
  getProductsByStoreId(id:number):Observable<any>{
    return this.http.get(`${environment.API_BASE_URL}/product/getByStoreId/${id}`);
  }


  // getStoresByName(name:string):Observable<any>{
  //   return this.http.get(`${environment.apiUrl}/store/search/${name}`);
  // }

  // getStoresByAddress(name:string):Observable<any>{
  //   return this.http.get(`${environment.apiUrl}/store/filter/${name}`);
  // }
}
