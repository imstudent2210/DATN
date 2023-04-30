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
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'src/environment/environment.prod';
import { CategoriesService } from 'src/app/services/categories.service';
import { SizeService } from 'src/app/services/size.service';
import { StoresService } from 'src/app/services/stores.service';
import { NgToastService } from 'ng-angular-popup';
@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent {
  constructor(
    private service: ProductsService,
    private category: CategoriesService,
    private store: StoresService,
    private sizeService: SizeService,
    private route: Router,
    public imageDialog: MatDialog,
    public deleteDialog: MatDialog,
    private http: HttpClient,
    private toast: NgToastService) { }

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  filterForm = new FormGroup({
    name: new FormControl(),
    price: new FormControl(),
    inventory: new FormControl(),
    category: new FormControl(),
    store: new FormControl(),
    size: new FormControl()
  });

  filterResult: any[] = [];
  filterResultLength?:any;
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

  filter(): void {
    let search = '';
    const name = this.filterForm.get('name')?.value;
    if (name) {
      search += `name=="*${name}*";`;
    }
    const price = this.filterForm.get('price')?.value;
    if (price) {
      search += `price${price};`;
    }
    const inventory = this.filterForm.get('inventory')?.value;
    if (inventory) {
      search += `inventory${inventory};`;
    }
    const category = this.filterForm.get('category')?.value;
    if (category) {
      search += `category==${category};`;
    }
    const store = this.filterForm.get('store')?.value;
    if (store) {
      search += `store==${store};`;
    }

    if (search) {
      search = search.slice(0, -1); // Remove trailing semicolon
      const url = `${environment.API_BASE_URL}/rsql/product?search=${search}`;
      this.http.get<any[]>(url)
        .subscribe( data => {
          console.log(url);
          this.listProduct = data;
          this.filterResultLength = this.listProduct.length;
          if(this.listProduct.length==0){

            this.toast.success({ detail: "Thông báo", summary: "Không có dữ liệu!", duration: 3000 })
          }
          this.products = new MatTableDataSource<Store>(this.listProduct);
          this.products.sort = this.sort;
          this.products.paginator = this.paginator;
          this.productNumber = data.length;
        });
    }
  }
  categories?: any;
  getCategories(): void {
    this.category.getCategories().subscribe(
      data => {
        this.categories = data
      }
    )
  }

  stores?: any;
  getStores(): void {
    this.store.getStores().subscribe(
      data => {
        this.stores = data;
      }
    )
  }
  sizes?: any;
  getSize(): void {
    this.sizeService.getSize().subscribe(
      data => {
        this.sizes = data;
      }
    )
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
    // this.filter();
    this.getCategories();
    this.getSize();
    this.getStores()
  }
}
