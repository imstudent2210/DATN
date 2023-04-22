import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Staff } from '../model/staff.model';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  constructor(private http: HttpClient) { }
  getStaff(): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/staff/get`);
  }
  getStaffById(id: any) {
    return this.http.get<Staff>(`${environment.API_BASE_URL}/staff/get/${id}`);
  }
  createStaff(staff: any): Observable<any> {
    return this.http.post<Staff>(`${environment.API_BASE_URL}/staff/create`, staff);
  }

  updateStaff(staff: any, id: any): Observable<any> {
    return this.http.put<Staff>(`${environment.API_BASE_URL}/staff/update/${id}`, staff);
  }
   deleteStaff(id:any){
    return this.http.delete(`${environment.API_BASE_URL}/staff/delete/${id}`);
  }

  sendmail(email:any): Observable<any> {
    return this.http.post(`${environment.API_BASE_URL}/api/auth/sendmail`, email);
  }
}
