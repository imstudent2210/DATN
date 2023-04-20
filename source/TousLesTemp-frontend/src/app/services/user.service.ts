import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environment/environment.prod';

const httpOptions = {
		  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
		};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(environment.API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(environment.API_URL + 'user', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(environment.API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(environment.API_URL + 'admin', { responseType: 'text' });
  }

  getCurrentUser(): Observable<any> {
    return this.http.get(environment.API_URL + 'user/me', httpOptions);
  }
}
