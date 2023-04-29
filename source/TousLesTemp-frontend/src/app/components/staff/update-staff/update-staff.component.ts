import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { StaffService } from 'src/app/services/staff.service';
import { StaffGroupService } from 'src/app/services/staffgroup.service';
import { StoresService } from 'src/app/services/stores.service';
import { Staff } from 'src/app/model/staff.model';
import { MyErrorStateMatcher } from '../create-staff/create-staff.component';
import { Observable, finalize } from 'rxjs';
import { AngularFireStorage } from '@angular/fire/compat/storage';

@Component({
  selector: 'app-update-staff',
  templateUrl: './update-staff.component.html',
  styleUrls: ['./update-staff.component.scss']
})
export class UpdateStaffComponent implements OnInit {
  progress?: number;
  cdRef: any;
  constructor(private store: StoresService,
    private toast: NgToastService,
    private route: Router, 
    private activatedRoute: ActivatedRoute,
    private staffGroupService: StaffGroupService,
    private staffService: StaffService, 
    private storage: AngularFireStorage) { }

  stId = 0;

  private firebasePath = '/uploads/staff';
  fileUpload?: any[];
  imageUrl?: string;
  fireBaseUrl?: string;
  downloadURL?: Observable<string>;

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
          console.log(this.currentStaff.image);
          this.downloadURL.subscribe(url => {
            if (url) {
              this.fireBaseUrl = url;
            }
            console.log(this.fireBaseUrl);
            this.currentStaff.image = this.fireBaseUrl;
          });
        })
      )
      .subscribe((snapshot) => {
        const progress = (snapshot!.bytesTransferred / snapshot!.totalBytes) * 100;
        console.log(`Upload is ${progress}% done`);
        this.progress = Math.round(progress);
        this.cdRef.detectChanges(); 
      });
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
      .subscribe(
        data => {
          this.currentStaff = data
        }
      )
  }

  updateStaff() {
    if (this.currentStaff.name == '' || this.currentStaff.name == null || this.currentStaff.phone == ''
      || this.currentStaff.phone == null || this.currentStaff.email == '' || this.currentStaff.email == null) {
      this.toast.error({ detail: "Thông báo lỗi", summary: "Vui lòng nhập đủ thông tin!", duration: 3000 })
      return;
    }
    this.staffService.updateStaff(this.currentStaff, this.stId).subscribe(
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
