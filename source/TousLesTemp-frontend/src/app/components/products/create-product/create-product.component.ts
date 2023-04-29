import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Observable } from 'rxjs';
import { CategoriesService } from 'src/app/services/categories.service';
import { StoresService } from 'src/app/services/stores.service';
import { finalize } from 'rxjs/operators';

import { ProductsService } from 'src/app/services/products.service';
import { NgToastService } from 'ng-angular-popup';
import { Product } from 'src/app/model/product.model';
import { SizeService } from 'src/app/services/size.service';
import { Router } from '@angular/router';
import { AngularFireStorage } from '@angular/fire/compat/storage';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.scss']
})
export class CreateProductComponent implements OnInit {
  constructor(
    private category: CategoriesService, private store: StoresService,
    private sizeService: SizeService,
    private productService: ProductsService,
    private toast: NgToastService,
    private route: Router,
    private storage: AngularFireStorage) { }

  matcher = new MyErrorStateMatcher();
  namef = new FormControl('', [Validators.required]);
  inventoryf = new FormControl('', [Validators.required]);
  pricef = new FormControl('', [Validators.required]);
  categoriesf = new FormControl('', Validators.required);
  progress?: number;
  cdRef: any;
  private firebasePath = '/uploads/products';

  fileUpload?: any[];
  imageUrl?: string;
  fireBaseUrl?: string;
  downloadURL?: Observable<string>;

  newProduct: Product = {
    name: "",
    description: "",
    inventory: 0,
    price: 0,
    category: { id: 1 },
    size: { id: 1 },
    store: { id: 1, address: {} },
    image: ""
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    const filePath = `${this.firebasePath}/${file.name}`;
    const fileRef = this.storage.ref(filePath);
    const uploadTask = this.storage.upload(filePath, file);
    uploadTask
      .snapshotChanges()
      .pipe(
        finalize(() => {
          this.downloadURL = fileRef.getDownloadURL();
          this.downloadURL.subscribe(url => {
            if (url) {
              this.fireBaseUrl = url;
              this.toast.success({ detail: "Thành công", summary: "Tải ảnh thành công!", duration: 3000 })
            }
            this.newProduct.image = this.fireBaseUrl;
          });
        })
      ).subscribe((snapshot) => {
        const progress = (snapshot!.bytesTransferred / snapshot!.totalBytes) * 100;
        console.log(`Upload is ${progress}% done`);
        this.progress = Math.round(progress);
        this.cdRef.detectChanges();
      });
  }
  categories?: any;
  getCategories(): void {
    this.category.getCategories().subscribe(
      data => {
        this.categories = data
      }
    )
  }
  categoriesActivated?: any;
  getCategoriesActivated() {
    this.category.getCategorisActivated().subscribe(
      data => {
        this.categoriesActivated = data
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
  createProduct() {
    if (this.newProduct.name == '' || this.newProduct.name == null || this.newProduct.inventory == 0
      || this.newProduct.inventory == null || this.newProduct.price == 0 || this.newProduct.price == null) {
      this.toast.error({ detail: "Thông báo lỗi", summary: "Vui lòng nhập đủ thông tin!", duration: 3000 })
      return;
    }

    this.productService.createProduct(this.newProduct).subscribe(
      (data) => {
        this.toast.success({ detail: "Thông báo thành công", summary: " Đã thêm sản phẩm!", duration: 3000 })
        this.route.navigate(['home/products/list']);
      },
      (error) => {
        console.log(error);
        this.toast.error({ detail: "Thông báo lỗi", summary: " Sản phẩm chưa được thêm!", duration: 3000 })
      }
    )
  }
  ngOnInit(): void {
    this.getCategories();
    this.getStores();
    this.getSize();
    this.getCategoriesActivated();
  }
}

