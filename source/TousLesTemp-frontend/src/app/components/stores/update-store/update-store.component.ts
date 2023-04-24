import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AddressService } from 'src/app/services/address.service';
import { StoresService } from 'src/app/services/stores.service';
import { Store } from 'src/app/model/store.model';
import { MyErrorStateMatcher } from '../create-store/create-store.component';
import { AngularFireStorage } from '@angular/fire/compat/storage';
import { Observable, finalize } from 'rxjs';

@Component({
  selector: 'app-update-store',
  templateUrl: './update-store.component.html',
  styleUrls: ['./update-store.component.scss']
})
export class UpdateStoreComponent {
  constructor(private addressService: AddressService, private storeService: StoresService,
    private toast: NgToastService, private route: Router,
    private activatedRoute: ActivatedRoute,  private storage: AngularFireStorage) { }
  sId = 0;
  matcher = new MyErrorStateMatcher();
  namef = new FormControl('', [Validators.required]);
  phonef = new FormControl('', [Validators.required]);
  emailf = new FormControl('', [Validators.required]);
  addressDetailf = new FormControl('', [Validators.required]);

  email = new FormControl('', [Validators.required, Validators.email]);
  private firebasePath = '/uploads';
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
  currentStore: Store = {
    name: "",
    email: "",
    phone: "",
    address: { id: 1 },
    addressDetail: ""
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
  getCurrentStore(id: number): void {
    this.storeService.getStoreById(id)
      .subscribe(
        data => {
          this.currentStore = data
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
          console.log(this.currentStore.image);
          this.downloadURL.subscribe(url => {
            if (url) {
              this.fireBaseUrl = url;
              this.toast.success({detail:"Thành công", summary:"Tải ảnh thành công!", duration:3000})
            }
            console.log(this.fireBaseUrl);
            this.currentStore.image = this.fireBaseUrl;
          });
        })
      )
      .subscribe(url => {
        if (url) {
          console.log(url);
        }
      });
  }


  updateStore() {
    this.storeService.updateStore(this.currentStore, this.sId).subscribe(
      (data) => {
        console.log(data);
        this.toast.success({ detail: "Thông báo thành công", summary: " Đã cập nhật cửa hàng!", duration: 3000 })
        this.route.navigate(['home/stores/list']);
      },
      (error) => {
        console.log(error);
        this.toast.error({ detail: "Thông báo lỗi", summary: " Cửa hàng chưa được cập nhật!", duration: 3000 })

      }
    )
  }

  //=========================================
  ngOnInit(): void {
    this.sId = this.activatedRoute.snapshot.params['sid'];
    this.getCurrentStore(this.sId);
    this.getAddress();

  }
}
