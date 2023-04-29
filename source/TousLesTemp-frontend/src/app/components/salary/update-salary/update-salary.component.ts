import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Salary } from 'src/app/model/salary.model';
import { SalaryService } from 'src/app/services/salary.service';
import { MyErrorStateMatcher } from '../../register/register.component';

@Component({
  selector: 'app-update-salary',
  templateUrl: './update-salary.component.html',
  styleUrls: ['./update-salary.component.scss']
})
export class UpdateSalaryComponent implements OnInit {

  constructor(private salaryService: SalaryService,
    private route: Router,
    private activatedRoute: ActivatedRoute,
    private toast: NgToastService) { }

  saId = 0;
  namef = new FormControl('', [Validators.required]);
  salaryf = new FormControl('', [Validators.required]);
  matcher = new MyErrorStateMatcher();
  isChecked = true;

  currentSalary: Salary = {
    id: 0,
    name: "",
    basicSalary: 0
  }

  getCurrentSalary(id: number): void {
    this.salaryService.getSalaryById(id)
      .subscribe(
        data => {
          this.currentSalary = data
        }
      )
  }

  updateSalary() {
    this.salaryService.updateSalary(this.currentSalary, this.saId)
      .subscribe(
        (data) => {
          console.log(data);
          this.toast.success({ detail: "Thông báo thành công", summary: " Đã cập nhật!", duration: 3000 })
          this.route.navigate(['home/salary/list']);
        },
        (error) => {
          console.log(error);
          this.toast.error({ detail: "Thông báo lỗi", summary: " Cập nhật không thành công!", duration: 3000 })
        }
      )
  }

  ngOnInit() {
    this.saId = this.activatedRoute.snapshot.params['said'];
    this.getCurrentSalary(this.saId);
  }
}

