import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { LoginService } from '../services/login.service';
import { environment } from '../environment/environment.prod';
import { and } from 'firebase/firestore';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private login: LoginService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.login.getToken();

    if (token != null && (!authReq.url.includes('maps.google.com'))) {
        authReq = authReq.clone({ setHeaders: { Authorization: `Bearer ${token}` }});
    }
    return next.handle(authReq);

  }
}

export const AuthInterceptorProvider = [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true,
  }
]
