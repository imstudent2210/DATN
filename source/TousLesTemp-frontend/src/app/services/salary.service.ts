import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class SalaryService {

  constructor(private http: HttpClient) { }

  getSalary(): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/staff-salary/get`);
  }
  deleteSalary(id :any): Observable<any> {
    return this.http.delete(`${environment.API_BASE_URL}/staff-salary/delete/${id}`);
  }
  updateSalary(salary: any, id: number): Observable<any> {
    return this.http.put(`${environment.API_BASE_URL}/staff-salary/update/${id}`, salary);
  }
  getSalaryById(id: number): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/staff-salary/get/${id}`);
  }
  createSalary(salary: any): Observable<any> {
    return this.http.post<any>(`${environment.API_BASE_URL}/staff-salary/create`, salary);
  }
}
