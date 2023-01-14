import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { KeycloakSecurityService } from 'src/app/service/keycloak-security.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent /* implements OnInit */{

  constructor(public securityService:KeycloakSecurityService,private route:Router){}
  
  title='menu';
  ngOnInit(): void{

  }

  onLogout(){
    this.securityService.kc?.logout();
    //this.route.navigate(['home']);
  }

  onLogin(){
    this.securityService.kc?.login();
  }

  onChangePass(){
    this.securityService.kc?.accountManagement();
  }

  IsManager(){
    //return this.securityService.kc?.hasResourceRole("manager","admin")
   return this.securityService.kc?.hasRealmRole('manager');
  }

  IsAdmin(){
    //return this.securityService.kc?.hasResourceRole("manager","admin")
   return this.securityService.kc?.hasRealmRole('admin');
  }
}
