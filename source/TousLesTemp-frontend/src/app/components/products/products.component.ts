import { Component, Input, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductsService } from 'src/app/services/products.service';
import { Product } from 'src/app/share/product.module';
import { Store } from 'src/app/share/store.module';
import { ImageDialogComponent } from './image-dialog/image-dialog.component';
import { ImageProcessingService } from 'src/app/services/image-processing.service';
import { map } from 'rxjs';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent {
  constructor(
    private service: ProductsService,
    private route: Router,
    public imageDialog: MatDialog,
    private imageProcessing: ImageProcessingService,
    public deleteDialog: MatDialog) { }

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  pId:any;
  listProduct?: any;
  item?: any;
  products?: any;
  columns: string[] = ['name', 'description', 'price', 'inventory', 'category', 'size', 'store', 'image', 'edit', 'delete']

  pageSizeOptions = [5, 10, 25, 50];
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }


  doFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.products.filter = filterValue;
    console.log(filterValue);
  }

  showImages(product: Product) {
    console.log(product);
    this.imageDialog.open(ImageDialogComponent, {
      data: {
        images: product.images
      },
      height: '400px',
      width: '600px'
    })
  }
  editProduct(pId: any) {
    this.route.navigate(["/home/products/update", pId]);
  }

  // ================== Call Api BackEnd====================
  getProducts(): void {
    this.service.getProducts()
      .pipe(
        map((x: Product[], i) => x.map((product: Product) => this.imageProcessing.createImages(product)))
      )
      .subscribe(
        data => {
          this.listProduct = data
          console.log(this.listProduct);
          this.products = new MatTableDataSource<Store>(this.listProduct);
          this.products.sort = this.sort;
          this.products.paginator = this.paginator;
        }
      )
  }


  deleteProduct(pId: number) {
    this.deleteDialog.open(DeleteDialogComponent, {
      data: {
        productId: pId
      }
    }).afterClosed().subscribe(result => {
      this.service.getProducts()
        .pipe(
          map((x: Product[], i) => x.map((product: Product) => this.imageProcessing.createImages(product)))
        )
        .subscribe(
          data => {
            this.listProduct = data;
            console.log(this.listProduct);

            console.log("êrr");
          })
    });

  }

  ngOnInit(): void {
    this.getProducts();


  }
}
