import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';

import {UserAppService} from '../service/user-app.service';
import {Observable} from 'rxjs';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private user: UserAppService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('мы в интерцкпторе')
    let authReq: HttpRequest<any>;
    const token = this.user.getToken();
    let tok:string;
    if (token != null) {
      console.log('токен ОК')
      tok = 'Bearer ' + token;
    }else {
      console.log('токен не ОК')
      tok='';
    }
    authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, tok) });
    return next.handle(authReq);
  }
}


