import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment.prod';
import { Category } from '../model/category.model';

@Injectable({
  providedIn: 'root'
})
export class StaffGroupService{
  constructor(private http: HttpClient) { }
  getStaffGroup(): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/staffgroup/get`);
  }
}
