import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class SizeService {

  constructor(private http:HttpClient) { }

  getSize():Observable<any>{
    return this.http.get(`${environment.API_BASE_URL}/size/get`);
  }

}
