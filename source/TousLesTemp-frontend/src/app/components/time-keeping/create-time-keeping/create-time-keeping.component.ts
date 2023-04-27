import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { NgToastService } from 'ng-angular-popup';
import { TimeKeepingService } from 'src/app/services/time-keeping.service';
import { MyErrorStateMatcher } from '../../register/register.component';
import { TimeKeeping } from 'src/app/model/time-keeping.model';
import { StaffService } from 'src/app/services/staff.service';
import { SalaryService } from 'src/app/services/salary.service';
import { StoresService } from 'src/app/services/stores.service';

@Component({
  selector: 'app-create-time-keeping',
  templateUrl: './create-time-keeping.component.html',
  styleUrls: ['./create-time-keeping.component.scss']
})
export class CreateTimeKeepingComponent implements OnInit {
  constructor(private timeKeepingService: TimeKeepingService, private route: Router,
    private toast: NgToastService, private staffService: StaffService,
    private salaryService: SalaryService, private storeService: StoresService) { }

  tId = 0;
  monthf = new FormControl('', [Validators.required]);
  numOfShift = new FormControl('', [Validators.required]);
  salaryf = new FormControl('', [Validators.required]);
  matcher = new MyErrorStateMatcher();


  isChecked = true;

  newTimeKeeping: TimeKeeping = {
    month: 1,
    numOfShift: 0,
    salary: { id: 1 },
    staff: { id: 17, staffGroup: {}, phone: '', image: '', email: '', store: { address: {} } }
  }

  createTimeKeeping() {
    this.timeKeepingService.createTimeKeeping(this.newTimeKeeping)
      .subscribe(
        (data) => {
          console.log(data);
          this.toast.success({ detail: "Thông báo thành công", summary: " Đã tạo bảng chấm công mới!", duration: 3000 })
          this.route.navigate(['home/timekeeping/list']);
        },
        (error) => {
          console.log(error);
          this.toast.error({ detail: "Thông báo lỗi", summary: " Tạo mới không thành công!", duration: 3000 })
        }
      )
  }

  month = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']
  staff?: any;
  getStaff(): void {
    this.staffService.getStaff().subscribe(
      data => {
        this.staff = data
      }
    )
  }
  // stores-id?:number;
  storesId?: any;
  salaryList?: any;
  getSalary(): void {
    this.salaryService.getSalary().subscribe(
      data => {
        this.salaryList = data
      }
    )
  }
  stores?: any;
  getStores(): void {
    this.storeService.getStores().subscribe(
      data => {
        this.stores = data;
      }
    )
  }

  getStaffByStore(storesId: any): void {
    this.staffService.getStaffByStoreId(this.storesId).subscribe(
      data => {
        this.staff = data
      }
    )
  }
  ngOnInit(): void {
    this.getSalary();
    this.getStaff();
    this.getStores();

  }
}


