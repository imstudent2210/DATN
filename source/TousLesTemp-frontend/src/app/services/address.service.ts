import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http:HttpClient) { }
  getAddress():Observable<any>{
    return this.http.get(`${environment.apiUrl}/address/get`);
  }
}
