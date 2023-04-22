import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment.prod';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(credentials:any): Observable<any> {
    return this.http.post(environment.AUTH_API + '/signin', {
      email: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user:any): Observable<any> {
    return this.http.post(environment.AUTH_API + '/signup', {
      displayName: user.displayName,
      email: user.email,
      password: user.password,
      matchingPassword: user.matchingPassword,
      socialProvider: 'LOCAL',
      using2FA: user.using2FA
    }, httpOptions);
  }

  verify(credentials:any): Observable<any> {
    return this.http.post(environment.AUTH_API + '/verify', credentials.code, {
    	  headers: new HttpHeaders({ 'Content-Type': 'text/plain' })
    });
  }

  sendmail(email:any): Observable<any> {
    return this.http.post(`${environment.API_BASE_URL}/sendmail`, email);
  }

public loginToken(token: any) {
  sessionStorage.setItem('auth-token', token);
  return true;
}
public isLogin() {
  let token = sessionStorage.getItem('auth-token');
  if (token == undefined || token == '' || token == null) {
    return false;
  }
  return true;
}
public isLogout() {
  sessionStorage.removeItem('auth-token');
  sessionStorage.removeItem('auth-user');
  return true;
}
public getToken() {
  return sessionStorage.getItem('auth-token');
}
public setUser(user: any) {
  sessionStorage.setItem('auth-user', JSON.stringify(user));
}
public getUser() {
  let user = sessionStorage.getItem('auth-user');
  if (user != null) return JSON.parse(user);
  else {
    this.isLogout();
    return null;
  }
}
public getUserRole() {
  let user = this.getUser();
  return user.roles[0];
}
public getUserName() {
  let username = this.getUser().displayName;
  return username;
}
public getEmail() {
  let email = this.getUser().email;
  return email;
}

}
