import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { MenuComponent } from './component/menu/menu.component';
import { ProductsComponent } from './component/products/products.component';
import { SuppliersComponent } from './component/suppliers/suppliers.component';

const routes: Routes = [
  {path:'',component:MenuComponent},
  {path:'home',component:MenuComponent},
  {path:"login",component:LoginComponent},
  {path:'**',redirectTo:'menu',pathMatch:'full'},
  {path:'supplier',component:SuppliersComponent},
  {path:'product',component:ProductsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
