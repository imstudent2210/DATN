import { Component, Input, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { StoresService } from 'src/app/services/stores.service';
import { CustomPaginator } from 'src/app/share/paginator-config';
import { Store } from 'src/app/share/store.module';
// import { MatPaginatorIntl } from '@angular/material';
@Component({
  selector: 'app-stores',
  templateUrl: './stores.component.html',
  styleUrls: ['./stores.component.scss']
})
export class StoresComponent {
  constructor(private service: StoresService) { }
  @ViewChild(MatPaginator) paginator?:MatPaginator;
  @ViewChild(MatSort) sort?:MatSort;
  @Input() name?:any;

  empty?:any;
  stores?:any;
  columns: string[] = ['name', 'phone', 'email', 'address']

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
        this.empty = data
        console.log(this.empty);
        this.stores = new MatTableDataSource<Store>(this.empty);
        this.stores.sort = this.sort;
        this.stores.paginator = this.paginator;
      }
    )
  }

  getStoresByName(name:string):void{
    this.service.getStoresByName(name).subscribe(
      data=>{
        this.empty = data;
        console.log(this.empty);

      }
    )
  }

  getStoresByAddress(name:string):void{
    this.service.getStoresByAddress(name).subscribe(
      data=>{
        this.empty = data;
        console.log(this.empty);

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
    // this.getStoresByName(this.name);
    this.getStoresByAddress(this.name);
  }



  ngOnInit(): void {
    this.getStores();
  }


}
