import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http: HttpClient) {

   }
   public getAllAddress() {
    return this.http.get(`${environment.apiUrl}/address/get`);
  }
}
