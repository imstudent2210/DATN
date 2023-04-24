import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http:HttpClient) { }
  getAddress():Observable<any>{
    return this.http.get(`${environment.API_BASE_URL}/address/get`);
  }
  createAddress(address:any):Observable<any>{
    return this.http.post(`${environment.API_BASE_URL}/address/create`, address);
  }
  updateAddress(address: any, id: number): Observable<any> {
    return this.http.put(`${environment.API_BASE_URL}/address/update/${id}`, address);
  }
}
