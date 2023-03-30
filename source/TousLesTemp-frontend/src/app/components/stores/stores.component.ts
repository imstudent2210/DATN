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
  constructor(private service: StoresService) { }


  stores: Store[] = [];

  columns: string[] = ['id', 'name', 'phone', 'email', 'address']

  getStores(pageIndex: number, pageSize: number): void {
    this.service.getStores(pageIndex, pageSize).subscribe(
      data => {
        this.stores = data.content;
        this.length = data.totalElements;
        console.log(data);
      }
    )
  }

  // ================Paging frontend======================
  length = 50;
  pageSize = 10;
  pageIndex = 0;
  pageSizeOptions = [5, 10, 25];

  hidePageSize = false;
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  disabled = false;

  pageEvent?: PageEvent;

  handlePageEvent(e: PageEvent) {
    this.pageEvent = e;
    this.length = e.length;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
    this.getStores(this.pageIndex, this.pageSize)
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }


  // ================call api backend======================


  ngOnInit(): void {
    this.getStores(this.pageIndex, this.pageSize)
  }
}
