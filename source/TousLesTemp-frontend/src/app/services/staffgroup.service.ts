import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class StaffGroupService{
  constructor(private http: HttpClient) { }
  getStaffGroup(): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/staffgroup/get`);
  }

  updateStaffGroup(staffGroup:any, id:number): Observable<any> {
    return this.http.put(`${environment.API_BASE_URL}/staffgroup/update/${id}`, staffGroup);
  }

  getStaffGroupById(id:number):Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/staffgroup/get/${id}`);
  }
  createStafGroup(staffGroup:any): Observable<any> {
    return this.http.post(`${environment.API_BASE_URL}/staffgroup/create/`,staffGroup);
  }
  countStaffByGroup():Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/staffgroup/get/countStaffByGroup`);
  }
}
