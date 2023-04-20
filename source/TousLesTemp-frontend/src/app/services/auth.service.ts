import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConstants } from '../share/app.constants';
import { environment } from '../environment/environment.prod';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(credentials:any): Observable<any> {
    return this.http.post(environment.AUTH_API + 'signin', {
      email: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user:any): Observable<any> {
    return this.http.post(environment.AUTH_API + 'signup', {
      displayName: user.displayName,
      email: user.email,
      password: user.password,
      matchingPassword: user.matchingPassword,
      socialProvider: 'LOCAL',
      using2FA: user.using2FA
    }, httpOptions);
  }

  verify(credentials:any): Observable<any> {
    return this.http.post(environment.AUTH_API + 'verify', credentials.code, {
    	  headers: new HttpHeaders({ 'Content-Type': 'text/plain' })
    });
  }
}
