  import { Component, OnInit } from '@angular/core';
  import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
  import { ErrorStateMatcher, ThemePalette } from '@angular/material/core';
  import { Observable } from 'rxjs';
  import { CategoriesService } from 'src/app/services/categories.service';
  import { StoresService } from 'src/app/services/stores.service';
  import { map, startWith } from 'rxjs/operators';
  import { Store } from 'src/app/share/store.module';
  import { ProductsService } from 'src/app/services/products.service';
  import { NgToastService } from 'ng-angular-popup';
  import { FileHandle } from 'src/app/share/file-handle.module';
  import { DomSanitizer } from '@angular/platform-browser';
  import { Product } from 'src/app/share/product.module';
  import { SizeService } from 'src/app/services/size.service';
  import { ActivatedRoute, Router } from '@angular/router';


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
      private sizeService: SizeService, private productService: ProductsService,
      private toast: NgToastService, private sanitizer: DomSanitizer,
      private route: Router, private activatedRoute: ActivatedRoute) { }

    // Validators
    matcher = new MyErrorStateMatcher();
    namef = new FormControl('', [Validators.required]);
    inventoryf = new FormControl('', [Validators.required]);
    pricef = new FormControl('', [Validators.required]);
    categoriesf = new FormControl('', Validators.required);



    // Model
    newProduct: Product = {
      name: "",
      description: "",
      inventory: 0,
      price: 0,
      category: { id: 1 },
      size: {id:1},
      store: { id: 1, address: {} },
      images: []
    }

    //File
    onFileSelected(event: any) {
      if (event.target.files) {
        const file = event.target.files[0];
        const fileHandle: FileHandle = {
          file: file,
          url: this.sanitizer.bypassSecurityTrustUrl(
            window.URL.createObjectURL(file)
          )
        }
        this.newProduct.images?.push(fileHandle);
      }
    }
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
    removeImage(index: number) {
      this.newProduct.images.splice(index, 1)
    }

    // Call api
    categories?: any;
    getCategories(): void {
      this.category.getCategories().subscribe(
        data => {
          this.categories = data
        }
      )
    }
    categoriesActivated?:any;
    getCategoriesActivated(){
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
    // create new product
    createProduct(productForm: NgForm) {
      if (this.newProduct.name == '' || this.newProduct.name == null || this.newProduct.inventory == 0
        || this.newProduct.inventory == null || this.newProduct.price == 0 || this.newProduct.price == null) {
        this.toast.error({ detail: "Thông báo lỗi", summary: "Vui lòng nhập đủ thông tin!", duration: 3000 })
        return;
      }
      const productFormData = this.prepareFormData(this.newProduct);
      this.productService.createProduct(productFormData).subscribe(
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
    //=====================================
    ngOnInit(): void {
      this.newProduct = this.activatedRoute.snapshot.data['product'];
      this.getCategories();
      this.getStores();
      this.getSize();
      this.getCategoriesActivated();
    }
  }

