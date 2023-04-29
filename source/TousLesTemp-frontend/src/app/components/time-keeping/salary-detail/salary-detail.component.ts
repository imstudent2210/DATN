import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Staff } from 'src/app/model/staff.model';
import { StaffService } from 'src/app/services/staff.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { TimeKeepingService } from 'src/app/services/time-keeping.service';
import { MatTableDataSource } from '@angular/material/table';
import { Store } from 'src/app/model/store.model';
import { environment } from 'src/environment/environment.prod';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-salary-detail',
  templateUrl: './salary-detail.component.html',
  styleUrls: ['./salary-detail.component.scss']
})
export class SalaryDetailComponent implements OnInit {
  constructor(
    private toast: NgToastService, private timeKeepingService: TimeKeepingService,
    private route: Router, private activatedRoute: ActivatedRoute,
    private staffService: StaffService, private http: HttpClient) { }
  sdId = 0;

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  list?: any;
  item?: any;
  timekeeping?: any;
  columns: string[] = ['id', 'staff', 'month', 'createdDate', 'salaryname', 'salaryprice', 'numOfShift'];

  pageSizeOptions = [5, 10, 20];
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput
        .split(',')
        .map((str) => +str);
    }
  }

  getTimeKeeping(): void {
    this.timeKeepingService.getAllTimeKeepingPerStaff(this.sdId).subscribe((data) => {
      this.timekeeping = data
      console.log(this.timekeeping);
      this.list = new MatTableDataSource<Store>(this.timekeeping);
      this.list.sort = this.sort;
      this.list.paginator = this.paginator;

    });
  }

  currentStaff: Staff = {
    name: "",
    email: "",
    phone: "",
    store: { id: 1, address: {} },
    staffGroup: { id: 1 },
    image: ""
  }
  getCurrentStaff(id: number): void {
    this.staffService.getStaffById(id)
      .subscribe(
        data => {
          this.currentStaff = data
        }
      )
  }
  totalSalary: any;
  getShiftSalary() {
    this.timeKeepingService.getShiftSalary(this.sdId).subscribe((data) => {
      this.totalSalary = data
    });
  }
  getMonthSalary(id: any, month: any) {
    this.http.get(`${environment.API_BASE_URL}/staff-timekeeping/get-monthsalary?id=${id}&month=${month}`).subscribe((data) => {
      this.toast.success({
        detail: "Thông báo thành công", summary: " Đã xoá bảng chấm công thành công!", duration: 3000

      }), console.log(data)
    },
      (error) => {
        console.log(error);
        this.toast.error({ detail: "Thông báo lỗi", summary: " Không thế xoá bảng chấm công này!", duration: 3000 })
      })
  }

  ngOnInit(): void {
    this.sdId = this.activatedRoute.snapshot.params['sdid'];
    this.getCurrentStaff(this.sdId);
    this.getTimeKeeping();
    this.getShiftSalary();
  }
}
