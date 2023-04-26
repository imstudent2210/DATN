import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment.prod';
import { Store } from '../model/store.model';

@Injectable({
  providedIn: 'root'
})
export class StoresService {

  constructor(private http: HttpClient) { }

  getStores(): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/store/get`);
  }

  getStoresByName(name: string): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/store/search/${name}`);
  }

  getStoresByAddress(name: string): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/store/filter/${name}`);
  }

  getStoreById(id: number): Observable<any> {
    return this.http.get(`${environment.API_BASE_URL}/store/get/${id}`);
  }
  createStore(store: Store): Observable<any> {
    return this.http.post(`${environment.API_BASE_URL}/store/create`, store);
  }

  updateStore(store: Store, id:number): Observable<any> {
    return this.http.put(`${environment.API_BASE_URL}/store/update/${id}`, store);
  }
  deleteStore(id:any): Observable<any> {
    return this.http.delete(`${environment.API_BASE_URL}/store/delete/${id}`);
  }
}
