import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthHttpInterceptorService {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let tokenstr = localStorage.getItem('jwttoken');

    if(localStorage.getItem('jwttoken')){
      req =req.clone({
        headers: req.headers.set('Authorization', ''+tokenstr)
      });
    }
    return next.handle(req);
  }
}
