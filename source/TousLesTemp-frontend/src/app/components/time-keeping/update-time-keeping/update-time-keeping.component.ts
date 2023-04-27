import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { TimeKeeping } from 'src/app/model/time-keeping.model';
import { SalaryService } from 'src/app/services/salary.service';
import { StaffService } from 'src/app/services/staff.service';
import { TimeKeepingService } from 'src/app/services/time-keeping.service';
import { MyErrorStateMatcher } from '../../register/register.component';

@Component({
  selector: 'app-update-time-keeping',
  templateUrl: './update-time-keeping.component.html',
  styleUrls: ['./update-time-keeping.component.scss']
})
export class UpdateTimeKeepingComponent implements OnInit {

  constructor(private salaryService: SalaryService,
    private activatedRoute: ActivatedRoute, private timeKeepingService: TimeKeepingService, private route: Router,
    private toast: NgToastService, private staffService: StaffService) { }

  tId = 0;
  monthf = new FormControl('', [Validators.required]);
  salaryf = new FormControl('', [Validators.required]);
  numOfShift = new FormControl('', [Validators.required]);
  matcher = new MyErrorStateMatcher();
  isChecked = true;

  currentTimeKeeping: TimeKeeping = {
    id: 0,
    month: 0,
    numOfShift: 0,
    salary: { id: 1 },
    staff: { id: 1, staffGroup: {} , phone:'', image:'', email:'', store:{address:{}} }
  }
  getCurrentTimeKeeping(id: number): void {
    this.timeKeepingService.getTimeKeepingById(id)
      .subscribe(
        data => {
          this.currentTimeKeeping = data
        }
      )
  }

  updateTimpKeeping() {
    this.timeKeepingService.updateTimeKeeping(this.currentTimeKeeping, this.tId)
      .subscribe(
        (data) => {
          console.log(data);
          this.toast.success({ detail: "Thông báo thành công", summary: " Đã cập nhật!", duration: 3000 })
          this.route.navigate(['home/timekeeping/list']);
        },
        (error) => {
          console.log(error);
          this.toast.error({ detail: "Thông báo lỗi", summary: " Cập nhật không thành công!", duration: 3000 })
        }
      )
  }

  staff?: any;
  getStaff(): void {
    this.staffService.getStaff().subscribe(
      data => {
        this.staff = data
      }
    )
  }
  salaryList?: any;
  getSalary(): void {
    this.salaryService.getSalary().subscribe(
      data => {
        this.salaryList = data
      }
    )
  }
  ngOnInit() {
    this.tId = this.activatedRoute.snapshot.params['tid'];
    this.getCurrentTimeKeeping(this.tId);
    this.getSalary();
    this.getStaff();

  }
}


