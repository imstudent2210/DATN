import { Component, Input, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ProductsService } from 'src/app/services/products.service';
import { Product } from 'src/app/model/product.model';
import { Store } from 'src/app/model/store.model';
import { ImageDialogComponent } from './image-dialog/image-dialog.component';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import * as XLSX from 'xlsx';
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
    public deleteDialog: MatDialog) { }

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  productNumber?: number;
  pId: any;
  listProduct?: any;
  item?: any;
  products?: any;
  columns: string[] = ['name', 'price', 'inventory', 'category', 'size', 'store', 'image', 'edit', 'delete']

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
        image: product.image
      },
      height: '310px',
      width: '500px'
    })
  }
  editProduct(pId: any) {
    this.route.navigate(["/home/products/update", pId]);
  }

  getProducts(): void {
    this.service.getProducts()
      .subscribe(
        data => {
          this.listProduct = data;
          this.products = new MatTableDataSource<Store>(this.listProduct);
          this.products.sort = this.sort;
          this.products.paginator = this.paginator;
          this.productNumber = data.length;
        }
      )
  }

  deleteProduct(pId: number) {
    const dialogRef = this.deleteDialog.open(DeleteDialogComponent, {
      data: {
        productId: pId
      }
    });
    dialogRef.afterClosed().subscribe(() => {
      this.getProducts();
    });
  }

  fileName = "Export.xlsx";
  export(): void {
    let element = document.getElementById('productExport');
    const ws: XLSX.WorkSheet = XLSX.utils.table_to_sheet(element);
    const wb: XLSX.WorkBook = XLSX.utils.book_new();

    for (let i in ws) {
      console.log("ewewqe");

      if (typeof (ws[i]) != "object") continue;

      let cell = XLSX.utils.decode_cell(i);

      ws[i].style = {
        font: {
          name: "arial"
        },
        alignment: {
          vertical: "center",
          horizontal: "center",
          wrapText: '1',
        },
        border: {
          right: {
            style: "thin",
            color: "000000"
          },
          left: {
            style: "thin",
            color: "000000"
          },
        }
      };

      if (cell.r % 2) {
        ws[i].style.fill = {
          patternType: "solid",
          fgColor: { rgb: "b2b2b2" },
          bgColor: { rgb: "b2b2b2" }
        };
      }
      XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
      XLSX.writeFile(wb, this.fileName);
    }
  }

  ngOnInit(): void {
    this.getProducts();
  }
}
