import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment';
import { Store } from '../share/store.module';

@Injectable({
  providedIn: 'root'
})
export class StoresService {

  constructor(private http: HttpClient) { }

  getStores(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/store/get`);
  }

  getStoresByName(name: string): Observable<any> {
    return this.http.get(`${environment.apiUrl}/store/search/${name}`);
  }

  getStoresByAddress(name: string): Observable<any> {
    return this.http.get(`${environment.apiUrl}/store/filter/${name}`);
  }

  getStoreById(id: number): Observable<any> {
    return this.http.get(`${environment.apiUrl}/store/get/${id}`);
  }
  createStore(store: Store): Observable<any> {
    return this.http.post(`${environment.apiUrl}/store/create`, store);
  }

  updateStore(store: Store, id:number): Observable<any> {
    return this.http.put(`${environment.apiUrl}/store/update/${id}`, store);
  }
}
