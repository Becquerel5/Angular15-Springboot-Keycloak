import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';


//declare var keycloak:any;

@Injectable({
  providedIn: 'root'
})
export class KeycloakSecurityService {
 public kc?:Keycloak;

 
  constructor() { }

  async init(){
    console.log("security initiliza!!!");
    this.kc=new Keycloak({
      url:"http://localhost:8080/",
      realm:"employee-realm",
      clientId:"AngularProductApp"
    });
    await this.kc?.init({
      //onLoad:'login-required'
      onLoad:'check-sso',
      
    });
    console.log(this.kc.token);
  }
}


/* {
  "realm": "employee-realm",
  "auth-server-url": "http://localhost:8080/",
  "ssl-required": "external",
  "resource": "employee-app",
  "public-client": true,
  "confidential-port": 0
} */