import { Component, Input, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ProductsService } from 'src/app/services/products.service';
import { Store } from 'src/app/share/store.module';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent {
  constructor(private service: ProductsService) { }
  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  empty?: any;
  products?: any;
  columns: string[] = ['description','image','inventory','name','price','category','size','store']

  pageSizeOptions = [3, 5, 10, 25];
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
  // ================== Call Api BackEnd====================
  getProducts(): void {
    this.service.getProducts().subscribe(
      data => {
        this.empty = data
        console.log(this.empty);
        this.products = new MatTableDataSource<Store>(this.empty);
        this.products.sort = this.sort;
        this.products.paginator = this.paginator;
      }
    )
  }

  // getStoresByName(name:string):void{
  //   this.service.getStoresByName(name).subscribe(
  //     data=>{
  //       this.empty = data;
  //       console.log(this.empty);

  //     }
  //   )
  // }
  search() {
    // this.getStoresByName(this.name);
    this.getProducts();
  }
  getCurrentItem(item: any) {
    console.log(item);
  }

  doFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.products.filter = filterValue;
    console.log(filterValue);
  }
  ngOnInit(): void {
    this.getProducts();
  }
}
