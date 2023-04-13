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
    return this.http.get<Staff>(`${environment.apiUrl}/staff/get2/${id}`);
  }

  // 1 usage
  createStaff(staff: FormData): Observable<any> {
    return this.http.post<Staff>(`${environment.apiUrl}/staff/create2`, staff);
  }
  updateStaff(staff: FormData, id: any): Observable<any> {
    return this.http.put<Staff>(`${environment.apiUrl}/staff/update2/${id}`, staff);
  }
  deleteStaff(id:any){
    return this.http.delete(`${environment.apiUrl}/staff/delete2/${id}`);
  }
  sendmail(email:any): Observable<any> {
    return this.http.post(`${environment.apiUrl}/sendmail`, email);
  }
}
