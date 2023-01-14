import { Component } from '@angular/core';

@Component({
  selector: 'app-suppliers',
  templateUrl: './suppliers.component.html',
  styleUrls: ['./suppliers.component.scss']
})
export class SuppliersComponent {
  title="Suppliers"
  suppliers:any

  ngOnInit(){
    this.suppliers=[
      {id:1,name:"scqscqc",supplier:"DTFB"},
      {id:2,name:"ddddd",supplier:"DTFB"},
      {id:3,name:"scdddqscqc",supplier:"DTFB"}
    ]
  }
}
