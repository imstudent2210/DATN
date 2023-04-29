import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NgToastService } from 'ng-angular-popup';
import { StaffService } from 'src/app/services/staff.service';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.scss']
})
export class DeleteDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
    private stafftService: StaffService,
    private toast: NgToastService) { }

  stId: any;

  reciveData() {
    console.log(this.data);
    this.stId = this.data.staffId;
  }

  deleteProduct() {
    this.stafftService.deleteStaff(this.stId).subscribe(
      (data) => {
        this.toast.warning({ detail: "Thông báo thành công", summary: " Đã xoá nhân viên!", duration: 3000 })
      }, (error) => {
        this.toast.error({ detail: "Thông báo lỗi", summary: " Không thể xoá nhân viên!", duration: 3000 })
      }
    )
  }
  ngOnInit(): void {
    this.reciveData();
  }
}
