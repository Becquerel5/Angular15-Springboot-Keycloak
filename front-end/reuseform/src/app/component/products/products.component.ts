import { Component } from '@angular/core';
import { ProductsService } from 'src/app/service/products.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent {
  title="Products"
  products: any;
  errormessage: any;

  constructor(private productservice:ProductsService){

  }

  ngOnInit(){

    this.productservice.getProducts().subscribe(data=>{
      this.products=data;
    },err=>{ 
      console.log(err);
      this.errormessage=err.error.error;

    })

    /* this.products=[
      {id:1,name:"dsdvsdv",price:515561},
      {id:2,name:"rrrr",price:3453453}
    ] */
  }


 // producs[""];
}
