import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { StaffGroup } from 'src/app/model/staffgroup.model';
import { StaffGroupService } from 'src/app/services/staffgroup.service';
import { MyErrorStateMatcher } from '../create-staff/create-staff.component';

@Component({
  selector: 'app-create-staffgroup',
  templateUrl: './create-staffgroup.component.html',
  styleUrls: ['./create-staffgroup.component.scss']
})
export class CreateStaffgroupComponent implements OnInit {
  constructor(private staffGroupService: StaffGroupService, private route: Router,
    private toast: NgToastService) { }

  cId = 0;
  namef = new FormControl('', [Validators.required]);
  activatedf = new FormControl('', [Validators.required]);
  matcher = new MyErrorStateMatcher();

  isChecked = true;
  newStaffGroup: StaffGroup = {
    name: "",
    activated: ""
  }

  createCategory() {
    this.staffGroupService.createStafGroup(this.newStaffGroup)
      .subscribe(
        (data) => {
          console.log(data);
          this.toast.success({ detail: "Thông báo thành công", summary: " Đã tạo mới!", duration: 3000 })
          this.route.navigate(['home/group/list']);
        },
        (error) => {
          console.log(error);
          this.toast.error({ detail: "Thông báo lỗi", summary: " Tạo mới không thành công!", duration: 3000 })
        }
      )
  }
  ngOnInit(): void { }
}
