import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';
import { Category } from '../share/category.module';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor(private http: HttpClient) { }

  getCategories(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/category/get`);
  }
  getCategorisActivated(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/category/getActivated`);
  }
  updateCategory(category: Category, id: number): Observable<any> {
    return this.http.put(`${environment.apiUrl}/category/update/${id}`, category);
  }
  getCategoryById(id: number): Observable<any> {
    return this.http.get(`${environment.apiUrl}/category/get/${id}`);
  }
  createCategory(category: Category): Observable<any> {
    return this.http.post<Category>(`${environment.apiUrl}/category/create`, category);
  }
}
