import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { CreateComponent } from './component/create/create.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MenuComponent } from './component/menu/menu.component';
import { ProductsComponent } from './component/products/products.component';
import { SuppliersComponent } from './component/suppliers/suppliers.component';
import { KeycloakSecurityService } from './service/keycloak-security.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { KeycloakinterceptorService } from './service/keycloakinterceptor.service';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CreateComponent,
    MenuComponent,
    ProductsComponent,
    SuppliersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule

  ],
  providers: [
    {provide:APP_INITIALIZER,deps:[KeycloakSecurityService],useFactory:kcFactory,multi:true},
    {provide:HTTP_INTERCEPTORS,useClass:KeycloakinterceptorService,multi:true}
  ],
  bootstrap: [AppComponent]
})


export class AppModule { }

export function kcFactory(kcSecurity:KeycloakSecurityService) {
  return()=> kcSecurity.init();
}