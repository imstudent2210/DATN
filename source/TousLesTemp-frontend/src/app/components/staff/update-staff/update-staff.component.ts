import { Component, OnInit } from '@angular/core';
import { FormControl, NgForm, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { StaffService } from 'src/app/services/staff.service';
import { StaffGroupService } from 'src/app/services/staffgroup.service';
import { StoresService } from 'src/app/services/stores.service';
import { FileHandle } from 'src/app/share/file-handle.module';
import { Staff } from 'src/app/share/staff.module';
import { MyErrorStateMatcher } from '../create-staff/create-staff.component';
import { map } from 'rxjs';
import { ImageProcessingService } from 'src/app/services/image-processing.service';

@Component({
  selector: 'app-update-staff',
  templateUrl: './update-staff.component.html',
  styleUrls: ['./update-staff.component.scss']
})
export class UpdateStaffComponent implements OnInit {
constructor(private store: StoresService,
  private toast: NgToastService, private sanitizer: DomSanitizer,
  private route: Router, private activatedRoute: ActivatedRoute,
  private staffGroupService: StaffGroupService,private imageProcessing: ImageProcessingService,
  private staffService: StaffService){}

  stId = 0;


  matcher = new MyErrorStateMatcher();
  phonef = new FormControl('', [Validators.required]);
  namef = new FormControl('', [Validators.required]);
  emailf = new FormControl('', [Validators.required]);

  // Model
  currentStaff: Staff = {
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
      this.currentStaff.images?.push(fileHandle);
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
    this.currentStaff.images.splice(index, 1)
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
  getCurrentStaff(id: number): void {
    this.staffService.getStaffById(id)
      .pipe(
        map((x: Staff) => {
          return this.imageProcessing.createImages(x);
        })
      )
      .subscribe(
        data => {
          this.currentStaff = data
        }
      )
  }

  updateStaff(staffForm: NgForm) {
    if (this.currentStaff.name == '' || this.currentStaff.name == null || this.currentStaff.phone == ''
      || this.currentStaff.phone == null || this.currentStaff.email == '' || this.currentStaff.email == null) {
      this.toast.error({ detail: "Thông báo lỗi", summary: "Vui lòng nhập đủ thông tin!", duration: 3000 })
      return;
    }
    const staffFormData = this.prepareFormData(this.currentStaff);
    this.staffService.updateStaff(staffFormData, this.stId).subscribe(
      (data) => {
        this.toast.success({ detail: "Thông báo thành công", summary: " Đã cập nhật!", duration: 3000 })
        this.route.navigate(['home/staff/list']);
      },
      (error) => {
        console.log(error);
        this.toast.error({ detail: "Thông báo lỗi", summary: " Cập nhật không thành công!", duration: 3000 })
      }
    )
  }


  ngOnInit(): void {
    this.getStaffGroup();
    this.getStores();

    this.stId = this.activatedRoute.snapshot.params['stid'];
    this.getCurrentStaff(this.stId);
  }


}
