import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { StaffGroupService } from 'src/app/services/staffgroup.service';
import { MyErrorStateMatcher } from '../create-staff/create-staff.component';
import { StaffGroup } from 'src/app/model/staffgroup.model';

@Component({
  selector: 'app-update-group',
  templateUrl: './update-group.component.html',
  styleUrls: ['./update-group.component.scss']
})
export class UpdateGroupComponent implements OnInit {
  constructor(private activatedRoute: ActivatedRoute, 
    private staffGoupService: StaffGroupService,
    private toast: NgToastService, 
    private route: Router) { }
  gId: any;
  cId = 0;
  namef = new FormControl('', [Validators.required]);
  activatedf = new FormControl('', [Validators.required]);
  matcher = new MyErrorStateMatcher();
  isChecked = true;
  currentGroup: StaffGroup = {
    id: 0,
    name: "",
    activated: ""
  }
  getCurrentStaff(id: number): void {
    this.staffGoupService.getStaffGroupById(id)
      .subscribe(
        data => {
          this.currentGroup = data
        }
      )
  }
  updateStaffGroup() {
    this.staffGoupService.updateStaffGroup(this.currentGroup, this.gId)
      .subscribe(
        (data) => {
          console.log(data);
          this.toast.success({ detail: "Thông báo thành công", summary: " Đã cập nhật!", duration: 3000 })
          this.route.navigate(['home/staff/group']);
        },
        (error) => {
          console.log(error);
          this.toast.error({ detail: "Thông báo lỗi", summary: " Cập nhật không thành công!", duration: 3000 })
        }
      )
  }
  ngOnInit(): void {
    this.gId = this.activatedRoute.snapshot.params['gid'];
    this.getCurrentStaff(this.gId);
  }
}
