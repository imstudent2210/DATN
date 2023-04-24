import { Component, OnInit } from '@angular/core';
import { AngularFireStorage } from '@angular/fire/compat/storage';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { Route, Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Observable, finalize } from 'rxjs';
import { AddressService } from 'src/app/services/address.service';
import { StoresService } from 'src/app/services/stores.service';
import { Store } from 'src/app/model/store.model';


export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
@Component({
  selector: 'app-create-store',
  templateUrl: './create-store.component.html',
  styleUrls: ['./create-store.component.scss']
})
export class CreateStoreComponent implements OnInit {
  constructor(private addressService: AddressService, private storeService:StoresService,
   private toast: NgToastService, private route:Router, private storage: AngularFireStorage) { }
  // Validators
  matcher = new MyErrorStateMatcher();
  namef = new FormControl('', [Validators.required]);
  phonef = new FormControl('', [Validators.required]);
  emailf = new FormControl('', [Validators.required]);
  addressDetailf = new FormControl('', [Validators.required]);

  email = new FormControl('', [Validators.required, Validators.email]);

  private firebasePath = '/uploads/store';

  fileUpload?: any[];
  imageUrl?: string;
  fireBaseUrl?:string;
  downloadURL?: Observable<string>;

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'Vui lòng nhập email';
    }

    return this.email.hasError('email') ? 'Email không hợp lệ' : '';
  }
  //Model
  newStore: Store = {
    name: "",
    email: "",
    phone: "",
    address: { id: 1 },
    addressDetail:"",
    image: ""
  }
  // get list address
  address?: any;
  getAddress(): void {
    this.addressService.getAddress().subscribe(
      data => {
        this.address = data
        console.log(this.address);
      }
    )
  }


  //================== Call Api=======================
  createStore() {
    this.storeService.createStore(this.newStore).subscribe(
      (data) => {
        console.log(data);
        this.toast.success({ detail: "Thông báo thành công", summary: " Đã thêm cửa hàng!", duration: 3000 })
        this.route.navigate(['home/stores/list']);
      },
      (error) => {
        console.log(error);
        this.toast.error({ detail: "Thông báo lỗi", summary: " Cửa hàng chưa được thêm!", duration: 3000 })

      }
    )
  }


  onFileSelected(event:any) {
    const file = event.target.files[0];
    const filePath = `${this.firebasePath}/${file.name}`;
    const fileRef = this.storage.ref(filePath);
    const uploadTask = this.storage.upload(filePath, file);
    uploadTask
      .snapshotChanges()
      .pipe(
        finalize(() => {
          this.downloadURL = fileRef.getDownloadURL();
          console.log(this.newStore.image);
          this.downloadURL.subscribe(url => {
            if (url) {
              this.fireBaseUrl = url;
              this.toast.success({detail:"Thành công", summary:"Tải ảnh thành công!", duration:3000})
            }
            console.log(this.fireBaseUrl);
            this.newStore.image = this.fireBaseUrl;
          });
        })
      )
      .subscribe(url => {
        if (url) {
          console.log(url);
        }
      });
  }


//=========================================
  ngOnInit(): void {
    this.getAddress();
  }
}
