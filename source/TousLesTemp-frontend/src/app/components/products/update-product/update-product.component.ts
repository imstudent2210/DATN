import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Observable, finalize } from 'rxjs';
import { CategoriesService } from 'src/app/services/categories.service';
import { ProductsService } from 'src/app/services/products.service';
import { SizeService } from 'src/app/services/size.service';
import { StoresService } from 'src/app/services/stores.service';
import { Product } from 'src/app/model/product.model';
import { MyErrorStateMatcher } from '../create-product/create-product.component';
import { AngularFireStorage } from '@angular/fire/compat/storage';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.scss']
})
export class UpdateProductComponent {
  constructor(private category: CategoriesService, private store: StoresService, private sizeService: SizeService,
    private productService: ProductsService, private toast: NgToastService, private sanitizer: DomSanitizer,
    private route: Router, private activatedRoute: ActivatedRoute, private storage: AngularFireStorage) { }

  // Validators
  matcher = new MyErrorStateMatcher();
  namef = new FormControl('', [Validators.required]);
  inventoryf = new FormControl('', [Validators.required]);
  pricef = new FormControl('', [Validators.required]);
  categoriesf = new FormControl('', Validators.required);

  pId = 0;

  currentProduct: Product = {
    name: "",
    description: "",
    inventory: 0,
    price: 0,
    category: { id: 0 },
    size: { id: 0 },
    store: { id: 0, address: {} },
    image: ""
  }
  private firebasePath = '/uploads/staff';
  fileUpload?: any[];
  imageUrl?: string;
  fireBaseUrl?: string;
  downloadURL?: Observable<string>;

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
          // console.log(this.newStaff.image);
          this.downloadURL.subscribe(url => {
            if (url) {
              this.fireBaseUrl = url;
              this.toast.success({ detail: "Thành công", summary: "Tải ảnh thành công!", duration: 3000 })

            }
            // console.log(this.fireBaseUrl);
            this.currentProduct.image = this.fireBaseUrl;
          });
        })
      )
      .subscribe(url => {
        if (url) {
          console.log(url);
        }
      });
  }

  // upate new product
  updateProduct() {
    if (this.currentProduct.name == '' || this.currentProduct.name == null || this.currentProduct.inventory == 0
      || this.currentProduct.inventory == null || this.currentProduct.price == 0 || this.currentProduct.price == null) {
      this.toast.error({ detail: "Thông báo lỗi", summary: "Vui lòng nhập đủ thông tin!", duration: 3000 })
      return;
    }
    this.productService.updateProduct2(this.currentProduct, this.pId)
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
