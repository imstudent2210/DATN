import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { CategoriesService } from 'src/app/services/categories.service';
import { ProductsService } from 'src/app/services/products.service';
import { SizeService } from 'src/app/services/size.service';
import { StoresService } from 'src/app/services/stores.service';
import { FileHandle } from 'src/app/share/file-handle.module';
import { Staff } from 'src/app/share/staff.module';
import { StaffGroupService } from 'src/app/services/staffgroup.service';
import { StaffService } from 'src/app/services/staff.service';
import { ErrorStateMatcher } from '@angular/material/core';
import { Mail } from 'src/app/share/mail.module';


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
  constructor( private store: StoresService,
    private toast: NgToastService, private sanitizer: DomSanitizer,
    private route: Router, private activatedRoute: ActivatedRoute,
    private staffGroupService: StaffGroupService,
    private staffService: StaffService) { }

  matcher = new MyErrorStateMatcher();
  phonef = new FormControl('', [Validators.required]);
  namef = new FormControl('', [Validators.required]);
  emailf = new FormControl('', [Validators.required]);

  // Model
  newStaff: Staff = {
    name: "",
    email: "",
    phone: "",
    store: { id: 1, address: {} },
    staffGroup: { id: 1 },
    images: []
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
      this.newStaff.images?.push(fileHandle);
    }
  }
  prepareFormData(staff: Staff): FormData {
    const formData = new FormData();
    formData.append(
      'staff',
      new Blob([JSON.stringify(staff)], { type: 'application/json' })
    );
    for (let index = 0; index < staff.images.length; index++) {
      formData.append(
        'file',
        staff.images[index].file,
        staff.images[index].file.name
      );
    }
    return formData;
  }
  removeImage(index: number) {
    this.newStaff.images.splice(index, 1)
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

  createStaff(staffForm: NgForm) {
    if (this.newStaff.name == '' || this.newStaff.name == null || this.newStaff.phone == ''
      || this.newStaff.phone == null || this.newStaff.email == '' || this.newStaff.email == null) {
      this.toast.error({ detail: "Thông báo lỗi", summary: "Vui lòng nhập đủ thông tin!", duration: 3000 })
      return;
    }
    const staffFormData = this.prepareFormData(this.newStaff);
    this.staffService.createStaff(staffFormData).subscribe(
      (data) => {
        this.toast.success({ detail: "Thông báo thành công", summary: " Đã thêm nhân viên!", duration: 3000 })
        this.route.navigate(['home/staff/list']);
      },
      (error) => {
        console.log(error);
        this.toast.error({ detail: "Thông báo lỗi", summary: " Nhân viên chưa được thêm!", duration: 3000 })
      }
    )
    this.sendmail.to = this.newStaff.email;
    this.staffService.sendmail(this.sendmail).subscribe(
      (data)=>{
        this.toast.success({ detail: "Thông báo thành công", summary: " Đã gửi thư xác nhận!", duration: 3000 })
      }
    )
  }

  sendmail: Mail = {
    to: "",
    subject: "Tạo thông tin thành công",
    message:"To confirm your account, please click here : http://localhost:4200/home/categories/list"
  }



  //============
  ngOnInit(): void {
    this.newStaff = this.activatedRoute.snapshot.data['staff'];
    this.getStaffGroup();
    this.getStores();

  }

}
