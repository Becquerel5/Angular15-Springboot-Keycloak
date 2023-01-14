import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KeycloakSecurityService } from './keycloak-security.service';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http:HttpClient,private keycloak:KeycloakSecurityService) { }


  public getProducts(){
    return this.http.get("http://localhost:8082/api/becquerel/products/")

  }

 

  /* public getProducts(){
    return this.http.get("http://localhost:8082/api/becquerel/products/",
      {
        headers:new HttpHeaders({Authorization:'Bearer '+this.keycloak.kc?.token})
      }

    )

  } */



}
