import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { StoresService } from 'src/app/services/stores.service';
import { CustomPaginator } from 'src/app/share/paginator-config';
import { Store } from 'src/app/share/store.module';
// import { MatPaginatorIntl } from '@angular/material';
@Component({
  selector: 'app-stores',
  templateUrl: './stores.component.html',
  styleUrls: ['./stores.component.scss']
})
export class StoresComponent implements OnInit {
  constructor(private service: StoresService) { }
  @ViewChild(MatPaginator) paginator?:MatPaginator;
  @ViewChild(MatSort) sort?:MatSort;
  @Input() name?:any;

  sId=0;
  listStore?:any;
  stores?:any;
  columns: string[] = ['name', 'phone', 'email', 'address','action']

  pageSizeOptions = [3, 5, 10, 25];
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
// ================== Call Api BackEnd====================
  getStores(): void {
    this.service.getStores().subscribe(
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
    this.service.getStoresByName(name).subscribe(
      data=>{
        this.listStore = data;
        console.log(this.listStore);
      }
    )
  }

  getStoresByAddress(name:string):void{
    this.service.getStoresByAddress(name).subscribe(
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
  getCurrentItem(item:any){
    console.log(item);
  }

  search(){
    this.getStoresByAddress(this.name);
  }
  ngOnInit(): void {
    this.getStores();
  }
}
