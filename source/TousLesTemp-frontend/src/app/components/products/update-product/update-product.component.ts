import { Component } from '@angular/core';
import { FormControl, Validators, NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Observable, startWith, map } from 'rxjs';
import { CategoriesService } from 'src/app/services/categories.service';
import { ProductsService } from 'src/app/services/products.service';
import { SizeService } from 'src/app/services/size.service';
import { StoresService } from 'src/app/services/stores.service';
import { FileHandle } from 'src/app/model/file-handle.model';
import { Product } from 'src/app/model/product.model';
import { MyErrorStateMatcher } from '../create-product/create-product.component';
import { ImageProcessingService } from 'src/app/services/image-processing.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.scss']
})
export class UpdateProductComponent {
  constructor(private category: CategoriesService, private store: StoresService, private sizeService: SizeService,
    private productService: ProductsService, private toast: NgToastService, private sanitizer: DomSanitizer,
    private route: Router, private activatedRoute: ActivatedRoute, private imageProcessing: ImageProcessingService) { }

  // Validators
  matcher = new MyErrorStateMatcher();
  namef = new FormControl('', [Validators.required]);
  inventoryf = new FormControl('', [Validators.required]);
  pricef = new FormControl('', [Validators.required]);
  categoriesf = new FormControl('', Validators.required);

  //======================
  pId = 0;
  // Model

  currentProduct: Product = {
    name: "",
    description: "",
    inventory: 0,
    price: 0,
    category: { id: 0 },
    size: {id:0},
    store: { id: 0, address: {} },
    images: []
  }


  // File
  prepareFormData(product: Product): FormData {
    const formData = new FormData();
    formData.append(
      'product',
      new Blob([JSON.stringify(product)], { type: 'application/json' })
    );
    for (let index = 0; index < product.images.length; index++) {
      formData.append(
        'file',
        product.images[index].file,
        product.images[index].file.name
      );
    }
    return formData;
  }
  onFileSelected(event: any) {
    if (event.target.files) {
      const file = event.target.files[0];
      const fileHandle: FileHandle = {
        file: file,
        url: this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(file)
        )
      }
      this.currentProduct.images?.push(fileHandle);
    }
  }
  removeImage(index: number) {
    this.currentProduct.images.splice(index, 1)
  }

  // =========== Call api===========
  categories: any;
  getCategories(): void {
    this.category.getCategories().subscribe(
      data => {
        this.categories = data
      }
    )
  }
  stores: any;
  getStores(): void {
    this.store.getStores().subscribe(
      data => {
        this.stores = data;
      }
    )
  }
  sizes: any;
  getSize(): void {
    this.sizeService.getSize().subscribe(
      data => {
        this.sizes = data;
      }
    )
  }

  // upate new product
  updateProduct(productForm: NgForm) {
    if (this.currentProduct.name == '' || this.currentProduct.name == null || this.currentProduct.inventory == 0
      || this.currentProduct.inventory == null || this.currentProduct.price == 0 || this.currentProduct.price == null) {
      this.toast.error({ detail: "Thông báo lỗi", summary: "Vui lòng nhập đủ thông tin!", duration: 3000 })
      return;
    }
    const productFormData = this.prepareFormData(this.currentProduct);
    this.productService.updateProduct2(productFormData, this.pId)
      .subscribe(
        (data) => {
          console.log(data);
          this.toast.success({ detail: "Thông báo thành công", summary: " Đã cập nhật sản phẩm!", duration: 3000 })
          this.route.navigate(['home/products/list']);
        },
        (error) => {
          console.log(error);
          this.toast.error({ detail: "Thông báo lỗi", summary: " Sản phẩm chưa được cập nhật!", duration: 3000 })
        }
      )
  }

  // currentProduct:any;
  getCurrentProduct(id: number): void {
    this.productService.getProductById(id)
      .pipe(
        map((x: Product) => {
          return this.imageProcessing.createImages(x);
        })
      )
      .subscribe(
        data => {
          this.currentProduct = data
        }
      )
  }

  //=====================================
  ngOnInit(): void {
    this.getCategories();
    this.getStores();
    this.getSize();
    this.pId = this.activatedRoute.snapshot.params['pid'];
    this.getCurrentProduct(this.pId);
  }
}
