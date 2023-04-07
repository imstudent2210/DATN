import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductsService } from 'src/app/services/products.service';
import { StoresService } from 'src/app/services/stores.service';

@Component({
  selector: 'app-store-detail',
  templateUrl: './store-detail.component.html',
  styleUrls: ['./store-detail.component.scss']
})
export class StoreDetailComponent implements OnInit {
  constructor(private route: ActivatedRoute, private service:StoresService,
    private productService: ProductsService) { }
  sId = 0;
  detail?:any;

  getStoreById(sId:number):void{
    this.service.getStoreById(this.sId).subscribe(
      (data:any) => {
        this.detail = data;
        console.log(this.detail);
      },
      (error) =>{
        console.log(error);
      }
    )
  }
  listProductByStoreId:any;
  getProductByStoreId(sId:number){
    this.productService.getProductsByStoreId(this.sId).subscribe(
      (data)=>{
        this.listProductByStoreId = data;
        console.log(this.listProductByStoreId);

      }
    )
  }


  ngOnInit(): void {
    this.sId = this.route.snapshot.params['sid'];
    console.log(this.sId);
    this.getStoreById(this.sId);
    this.getProductByStoreId(this.sId);
  }


}


