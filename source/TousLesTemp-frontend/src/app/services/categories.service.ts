import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {


  constructor(private http:HttpClient) { }

  getProductsPaging(page:number = 0, size:number = 5):Observable<any>{
    return this.http.get(`${environment.apiUrl}/product/paging?page=` + page.toString()+`&size=` + size.toString());
  }

  getCategories():Observable<any>{
    return this.http.get(`${environment.apiUrl}/category/get`);
  }
}
