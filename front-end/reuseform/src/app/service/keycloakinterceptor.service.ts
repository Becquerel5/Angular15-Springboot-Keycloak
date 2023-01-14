import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { KeycloakSecurityService } from './keycloak-security.service';

@Injectable({
  providedIn: 'root'
})
export class KeycloakinterceptorService implements HttpInterceptor{

  constructor(private security:KeycloakSecurityService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Http Interceptor.........");
    if (!this.security.kc?.authenticated) {
      return next.handle(req);
    }
    let request=req.clone({
      setHeaders:{
        Authorization:'Bearer '+this.security.kc.token
      }
    });
    return next.handle(request);
  }
}
