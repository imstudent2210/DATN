import { Component, Input, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ProductsService } from 'src/app/services/products.service';
import { Product } from 'src/app/share/product.module';
import { Store } from 'src/app/share/store.module';
import { ImageDialogComponent } from './image-dialog/image-dialog.component';
import { ImageProcessingService } from 'src/app/services/image-processing.service';
import { map } from 'rxjs';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent {
  constructor(private service: ProductsService, private route:Router,
              public imageDialog:MatDialog,private imageProcessing:ImageProcessingService) { }
  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  empty?: any;
  item?:any;
  products?: any;
  columns: string[] = ['name','description','price','inventory','category','size','store','image','action']

  pageSizeOptions = [5, 10, 25, 50];
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
  // ================== Call Api BackEnd====================
  getProducts(): void {
    this.service.getProducts()
    .pipe(
      map((x:Product[],i)=>x.map((product:Product)=>this.imageProcessing.createImages(product)))
    )
    .subscribe(
      data => {
        this.empty = data
        console.log(this.empty);
        this.products = new MatTableDataSource<Store>(this.empty);
        this.products.sort = this.sort;
        this.products.paginator = this.paginator;

      }
    )
  }

  search() {
    this.getProducts();
  }

  updateProduct(id:any){
    this.route.navigate(["/home/products/update", {id:id}]);
  }
  doFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.products.filter = filterValue;
    console.log(filterValue);
  }

  showImages(product:Product){
    console.log(product);
    this.imageDialog.open(ImageDialogComponent,{
      data:{
          images:product.images
      },
     height: '400px',
     width:'600px'
    })

  }

  ngOnInit(): void {
    this.getProducts();

  }
}
