import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { StoresService } from 'src/app/services/stores.service';
import { CustomPaginator } from 'src/app/model/paginator-config';
import { Store } from 'src/app/model/store.model';
import { NgToastService } from 'ng-angular-popup';
// import { MatPaginatorIntl } from '@angular/material';
@Component({
  selector: 'app-stores',
  templateUrl: './stores.component.html',
  styleUrls: ['./stores.component.scss']
})
export class StoresComponent implements OnInit {
  constructor(private storeService: StoresService, private route: Router, private toast: NgToastService) { }

  @ViewChild(MatPaginator) paginator?:MatPaginator;
  @ViewChild(MatSort) sort?:MatSort;
  @Input() name?:any;

  sId=0;
  listStore?:any;
  stores?:any;
  columns: string[] = ['name', 'phone', 'email', 'addressDetail','address','edit','delete']

  pageSizeOptions = [5, 10, 25];
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
// ================== Call Api BackEnd====================
  getStores(): void {
    this.storeService.getStores().subscribe(
      data => {
        this.listStore = data
        console.log(this.listStore);
        this.stores = new MatTableDataSource<Store>(this.listStore);
        this.stores.sort = this.sort;
        this.stores.paginator = this.paginator;
      }
    )
  }

  getStoresByName(name:string):void{
    this.storeService.getStoresByName(name).subscribe(
      data=>{
        this.listStore = data;
        console.log(this.listStore);
      }
    )
  }

  getStoresByAddress(name:string):void{
    this.storeService.getStoresByAddress(name).subscribe(
      data=>{
        this.listStore = data;
        console.log(this.listStore);
      }
    )
  }
  doFilter(event:Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.stores.filter = filterValue;
    console.log(filterValue);
  }
  // getCurrentItem(item:any){
  //   console.log(item);
  // }

  search(){
    this.getStoresByAddress(this.name);
  }

  editStore(sId:any){
    this.route.navigate(["/home/stores/update", sId]);
  }
  deleteStore(tId: number) {
    this.storeService.deleteStore(tId).subscribe((data) => {
      this.toast.success({ detail: "Thông báo thành công", summary: " Đã xoá cửa hàng thành công!", duration: 3000 })
      this.getStores();
    },
      (error) => {
        console.log(error);
        this.toast.error({ detail: "Thông báo lỗi", summary: " Không thế xoá cửa hàng này!", duration: 3000 })
      })
  };

  ngOnInit(): void {
    this.getStores();
  }
}
