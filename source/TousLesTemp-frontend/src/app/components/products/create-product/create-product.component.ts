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


interface Size {
  id: number;
  size: string;
}
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

  constructor(private category: CategoriesService, private store: StoresService,
    private productService: ProductsService, private toast: NgToastService, private sanitizer: DomSanitizer) { }

  // Validators
  matcher = new MyErrorStateMatcher();
  name = new FormControl('', [Validators.required]);
  selectFormControl = new FormControl('', Validators.required);
  //======================
  // model

  public newProduct = {
    name: "", description: "", inventory: "", price: "", image: [] , category: {
      cid: ''
    }, store: {
      stid: ''
    }, size: {
      sid: ''
    },
  };
  size: Size[] = [
    { id: 1, size: 'S' },
    { id: 2, size: 'M' },
    { id: 3, size: 'L' },
  ];


  createProduct: Product = {
    name: "",
    description: "",
    inventory: 0,
    price: 0,
    category:{
      id:0,
      // name:'',
      // activated:''
    },
    size:{
      id:0,
      // name:'',
      // activated:''
    },
    store:{
      id:0,
      // name:'',
      // activated:''
    },
    image:[]
  }
  //=========== Call api===========

  // function
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

  onFileSelected(event:any){
   if(event.target.files){
   const file = event.target.files[0];
   const fileHandle: FileHandle = {
    file: file,
    url: this.sanitizer.bypassSecurityTrustUrl(
      window.URL.createObjectURL(file)
    )
   }
  //  this.createProduct.image.push(fileHandle);
    this.createProduct.image?.push(fileHandle);
   }

  }
  createProduct2(productForm: NgForm){
    const productFormData = this.prepareFormData(this.createProduct);

    this.productService.createProduct2(productFormData).subscribe(
      (data)=>{
        console.log(data);
        this.toast.success({detail:"Thông báo thành công", summary:" Đã thêm sản phẩm!", duration:3000})
        productForm.reset();
      },
      (error)=>{
        console.log(error);
        this.toast.error({detail:"Thông báo lỗi", summary:" Sản phẩm chưa được thêm!", duration:3000})

      }
    )

  }

  prepareFormData(product:Product):FormData{
    const formData = new FormData();
    formData.append(
      'product',
      new Blob([JSON.stringify(product)], {type:'application/json'})
    );
    for (let index = 0; index < product.image.length; index++) {
      formData.append(
        'file',
        product.image[index].file,
        product.image[index].file.name
      );
    }
    return formData;
  }
  //======================
  myControl = new FormControl('');
  options: string[] = ['One', 'Two', 'Three'];

  filteredOptions?: Observable<string[]>;
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

  //======== ngOnInit ==========
  // formSubmit() {
    // this.productService.createProduct(this.newProduct).subscribe(
    //   (data) => {
    //     console.log(data);
    //     this.toast.success({ detail: "Thông báo thành công", summary: " Đã thêm sản phẩm!", duration: 3000 })
    //   },
    //   (error) => {
    //     console.log(error);
    //     this.toast.error({ detail: "Thông báo lỗi", summary: " Sản phẩm chưa được thêm!", duration: 3000 })
    //   }
    // )

  // }

  ngOnInit(): void {
    this.getCategories();
    this.getStores();
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );
  }
}

