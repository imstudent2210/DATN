import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductsService } from 'src/app/services/products.service';
import { StoresService } from 'src/app/services/stores.service';
import { Product } from 'src/app/share/product.module';
import { ImageDialogComponent } from '../../products/image-dialog/image-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { ImageProcessingService } from 'src/app/services/image-processing.service';
import { map } from 'rxjs';
import { Store } from 'src/app/share/store.module';

@Component({
  selector: 'app-store-detail',
  templateUrl: './store-detail.component.html',
  styleUrls: ['./store-detail.component.scss']
})
export class StoreDetailComponent implements OnInit {
  constructor(private route: ActivatedRoute, private service: StoresService,
    private productService: ProductsService,  public imageDialog: MatDialog,
    private imageProcessing: ImageProcessingService,) { }

  sId = 0;
  currentStore?: Store;
  columns: string[] = ['name', 'category', 'image', 'size','inventory', 'action']
  pageSizeOptions = [5, 10, 25, 50];
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }

  getStoreById(sId: number): void {
    this.service.getStoreById(this.sId).subscribe(
      (data: any) => {
        this.currentStore = data;
        console.log(this.currentStore);
      },
      (error) => {
        console.log(error);
      }
    )
  }
  listProductByStoreId: any;
  getProductByStoreId(sId: number) {
    this.productService.getProductsByStoreId(this.sId)
    .pipe(
      map((x: Product[], i) => x.map((product: Product) => this.imageProcessing.createImages(product)))
    )
    .subscribe(
      (data) => {
        this.listProductByStoreId = data;
        console.log(this.listProductByStoreId);

      }
    )
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

  ngOnInit(): void {
    this.sId = this.route.snapshot.params['sid'];
    console.log(this.sId);
    this.getStoreById(this.sId);
    this.getProductByStoreId(this.sId);
  }

}


