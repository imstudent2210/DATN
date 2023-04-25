import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class TimeKeepingService {

  constructor(private http: HttpClient) { }

  getTimeKeeping(): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/staff-timekeeping/get`);
  }
  deleteTimeKeeping(): Observable<any> {
    return this.http.delete(`${environment.API_BASE_URL}/staff-timekeeping/delete`);
  }
  updateTimeKeeping(timekeeping: any, id: number): Observable<any> {
    return this.http.put(`${environment.API_BASE_URL}/staff-timekeeping/update/${id}`,timekeeping );
  }
  getTimeKeepingById(id: number): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/staff-timekeeping/get/${id}`);
  }
  createTimeKeeping(timekeeping: any): Observable<any> {
    return this.http.post<any>(`${environment.API_BASE_URL}/staff-timekeeping/create`,timekeeping );
  }
  getAllTimeKeepingPerMonth(id: number): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/get-timekeeping-permonth/${id}`);
  }
  getShiftSalary(id: number): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/get-shiftsalary/${id}`);
  }
}
