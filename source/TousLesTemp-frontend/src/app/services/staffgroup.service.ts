import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';
import { Category } from '../share/category.module';

@Injectable({
  providedIn: 'root'
})
export class StaffGroupService{
  constructor(private http: HttpClient) { }
  getStaffGroup(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/staffgroup/get`);
  }
}
