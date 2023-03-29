import { Component } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { StoresService } from 'src/app/services/stores.service';
import { Store } from 'src/app/share/store.module';

@Component({
  selector: 'app-stores',
  templateUrl: './stores.component.html',
  styleUrls: ['./stores.component.scss']
})
export class StoresComponent {
  // constructor(private service: StoresService) { }
  // ngOnInit(): void {
  //   this.getStores(this.pageIndex, this.pageSize)
  // }

  // stores: Store[] = [];
  // columns: string[] = ['id', 'name', 'phone', 'email', 'address']

  // // ================Paging frontend======================
  // length = 20;
  // pageSize = 10;
  // pageIndex = 0;
  // pageSizeOption = [3, 5, 10];

  // hidePageSize = false;
  // showPageSizeOption = true;
  // showFirstLastButton = true;
  // disabled = false;

  // pageEvent?: PageEvent;

  // // ================call api backend======================
  // getStores(pageIndex: number, pageSize: number): void {
  //   this.service.getStores(pageIndex, pageSize).subscribe(
  //     data => {
  //       this.stores = data.content;
  //       this.length = data.totalElements;
  //       console.log(data);
  //     }
  //   )
  // }
  // handlePageEvent(e: PageEvent) {
  //   this.pageEvent = e;
  //   this.length = e.length;
  //   this.pageSize = e.pageSize;
  //   this.pageIndex = e.pageIndex;
  //   this.getStores(this.pageIndex, this.pageSize);
  // }
  // setPageSizeOption(setPageSizeOptionInput: string) {
  //   if (setPageSizeOptionInput) {
  //     this.pageSizeOption = setPageSizeOptionInput.split(',').map(str => +str);
  //   }
  // }


}
