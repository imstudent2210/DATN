import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment.prod';
import { Category } from '../model/category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor(private http: HttpClient) { }

  getCategories(): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/category/get`);
  }
  getCategorisActivated(): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/category/getActivated`);
  }
  updateCategory(category: Category, id: number): Observable<any> {
    return this.http.put(`${environment.API_BASE_URL}/category/update/${id}`, category);
  }
  getCategoryById(id: number): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/category/get/${id}`);
  }
  createCategory(category: Category): Observable<any> {
    return this.http.post<Category>(`${environment.API_BASE_URL}/category/create`, category);
  }
}
