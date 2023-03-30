import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';

@Injectable({
  providedIn: 'root'
})
export class StoresService {

  constructor(private http:HttpClient) { }

  getStores(page:number = 0, size:number = 5):Observable<any>{
    return this.http.get(`${environment.apiUrl}/store/paging?page=` + page.toString()+`&size=` + size.toString());
  }
}
