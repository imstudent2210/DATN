import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StoresService } from 'src/app/services/stores.service';

@Component({
  selector: 'app-store-detail',
  templateUrl: './store-detail.component.html',
  styleUrls: ['./store-detail.component.scss']
})
export class StoreDetailComponent implements OnInit {
  constructor(private route: ActivatedRoute, private service:StoresService) { }
  sId = 0;
  detail?:any;
  ngOnInit(): void {
    this.sId = this.route.snapshot.params['sid'];
    console.log(this.sId);
    this.getStoreById(this.sId);
  }

  getStoreById(id:number): void {
    this.service.getStoreById(id).subscribe(
      (data:any) => {
        this.detail = data;
        console.log(this.detail);
      },
      (error) =>{
        console.log(error);
      }
    )
  }




}
