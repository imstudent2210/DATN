import { Component, Input, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ProductsService } from 'src/app/services/products.service';
import { Store } from 'src/app/share/store.module';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent {
  constructor(private service: ProductsService, private route:Router) { }
  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  empty?: any;
  item?:any;
  products?: any;
  columns: string[] = ['name','description','price','inventory','category','size','store','image','action']

  pageSizeOptions = [5,3, 10, 25];
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
    console.log(item.id +"fffff"+ item.store.name);
    // alert("sản phẩm của cửa hàng: "+ item.store.name);

  }
  updateProduct(id:any){
    // console.log(id);
    this.route.navigate(["/home/products/create", {id:id}]);

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
