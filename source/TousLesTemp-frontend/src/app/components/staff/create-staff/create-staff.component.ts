import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { StoresService } from 'src/app/services/stores.service';
import { Staff } from 'src/app/model/staff.model';
import { StaffGroupService } from 'src/app/services/staffgroup.service';
import { StaffService } from 'src/app/services/staff.service';
import { ErrorStateMatcher } from '@angular/material/core';
import { Observable, finalize } from 'rxjs';
import { AngularFireStorage } from '@angular/fire/compat/storage';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-create-staff',
  templateUrl: './create-staff.component.html',
  styleUrls: ['./create-staff.component.scss']
})
export class CreateStaffComponent implements OnInit {

  constructor(private store: StoresService,
    private staffGroupService: StaffGroupService,
    private staffService: StaffService,
    private toast: NgToastService,
    private storage: AngularFireStorage,
    private route: Router) { }

  matcher = new MyErrorStateMatcher();
  phonef = new FormControl('', [Validators.required]);
  namef = new FormControl('', [Validators.required]);
  emailf = new FormControl('', [Validators.required]);

  private firebasePath = '/uploads/staff';

  progress?: number;
  cdRef: any;
  fileUpload?: any[];
  imageUrl?: string;
  fireBaseUrl?: string;
  downloadURL?: Observable<string>;

  newStaff: Staff = {
    name: "",
    email: "",
    phone: "",
    store: { id: 1, address: {} },
    staffGroup: { id: 1 },
    image: ""
  }

  stores?: any;
  getStores(): void {
    this.store.getStores().subscribe(
      data => {
        this.stores = data;
        console.log(this.stores);
      }
    )
  }
  staffGroups?: any;
  getStaffGroup(): void {
    this.staffGroupService.getStaffGroup().subscribe(
      data => {
        this.staffGroups = data
        console.log(this.staffGroups);
      }
    )
  }

  createStaff() {
    if (this.newStaff.name == '' || this.newStaff.name == null || this.newStaff.phone == ''
      || this.newStaff.phone == null || this.newStaff.email == '' || this.newStaff.email == null) {
      this.toast.error({ detail: "Thông báo lỗi", summary: "Vui lòng nhập đủ thông tin!", duration: 3000 })
      return;
    }
    this.staffService.createStaff(this.newStaff).subscribe(
      (data) => {
        this.toast.success({ detail: "Thông báo thành công", summary: " Đã thêm nhân viên!", duration: 3000 })
        this.route.navigate(['home/staff/list']);
      },
      (error) => {
        console.log(error);
        this.toast.error({ detail: "Thông báo lỗi", summary: " Nhân viên chưa được thêm!", duration: 3000 })
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
          this.downloadURL.subscribe(url => {
            if (url) {
              this.fireBaseUrl = url;
              this.toast.success({ detail: "Thành công", summary: "Tải ảnh thành công!", duration: 3000 })
            }
            this.newStaff.image = this.fireBaseUrl;
          });
        })
      ) .subscribe((snapshot) => {
        const progress = (snapshot!.bytesTransferred / snapshot!.totalBytes) * 100;
        console.log(`Upload is ${progress}% done`);
        this.progress = Math.round(progress);
        this.cdRef.detectChanges();
      });
  }

  ngOnInit(): void {
    this.getStaffGroup();
    this.getStores();
  }
}
