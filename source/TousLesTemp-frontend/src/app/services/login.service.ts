// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { environment } from '../environment/environment';
// import { catchError } from 'rxjs';

// @Injectable({
//   providedIn: 'root',
// })
// export class LoginService {
//   constructor(private http: HttpClient) { }
//   public generateToken(loginData: any) {
//     return this.http.post(`${environment.apiUrl}/login`, loginData);
//   }
//   public getCurrentUser() {
//     return this.http.get(`${environment.apiUrl}/current-user`);
//   }
//   //set token to localstorage
//   public loginToken(token: any) {
//     localStorage.setItem('token', token);
//     return true;
//   }
//   public isLogin() {
//     let token = localStorage.getItem('token');
//     if (token == undefined || token == '' || token == null) {
//       return false;
//     }
//     return true;
//   }
//   public isLogout() {
//     localStorage.removeItem('token');
//     localStorage.removeItem('user');
//     return true;
//   }
//   public getToken() {
//     return localStorage.getItem('token');
//   }
//   public setUser(user: any) {
//     localStorage.setItem('user', JSON.stringify(user));
//   }
//   public getUser() {
//     let user = localStorage.getItem('user');
//     if (user != null) return JSON.parse(user);
//     else {
//       this.isLogout();
//       return null;
//     }
//   }
//   public getUserRole() {
//     let user = this.getUser();
//     return user.authorities[0].authority;
//   }
//   public getUserName() {
//     let username = this.getUser().username;
//     return username;
//   }
//   public getEmail() {
//     let email = this.getUser().email;
//     return email;
//   }

// }
