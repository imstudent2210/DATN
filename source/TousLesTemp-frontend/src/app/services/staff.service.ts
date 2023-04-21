import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Staff } from '../share/staff.module';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  constructor(private http: HttpClient) { }
  getStaff(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/staff/get`);
  }
  getStaffById(id: any) {
    return this.http.get<Staff>(`${environment.apiUrl}/staff/get/${id}`);
  }
  createStaff(staff: any): Observable<any> {
    return this.http.post<Staff>(`${environment.apiUrl}/staff/create`, staff);
  }

  updateStaff(staff: any, id: any): Observable<any> {
    return this.http.put<Staff>(`${environment.apiUrl}/staff/update/${id}`, staff);
  }
   deleteStaff(id:any){
    return this.http.delete(`${environment.apiUrl}/staff/delete/${id}`);
  }

  sendmail(email:any): Observable<any> {
    return this.http.post(`${environment.apiUrl}/sendmail`, email);
  }
}
