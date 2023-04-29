import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Salary } from 'src/app/model/salary.model';
import { SalaryService } from 'src/app/services/salary.service';
import { MyErrorStateMatcher } from '../../register/register.component';

@Component({
  selector: 'app-create-salary',
  templateUrl: './create-salary.component.html',
  styleUrls: ['./create-salary.component.scss']
})
export class CreateSalaryComponent implements OnInit {
  constructor(private salaryService: SalaryService,
    private route: Router,
    private toast: NgToastService) { }

  saId = 0;
  namef = new FormControl('', [Validators.required]);
  salaryf = new FormControl('', [Validators.required]);
  matcher = new MyErrorStateMatcher();

  isChecked = true;
  newSalary: Salary = {
    name: "",
    basicSalary: 0
  }

  createCategory() {
    this.salaryService.createSalary(this.newSalary)
      .subscribe(
        (data) => {
          console.log(data);
          this.toast.success({ detail: "Thông báo thành công", summary: " Đã tạo mức lương mới!", duration: 3000 })
          this.route.navigate(['home/salary/list']);
        },
        (error) => {
          console.log(error);
          this.toast.error({ detail: "Thông báo lỗi", summary: " Tạo mới không thành công!", duration: 3000 })
        }
      )
  }
  ngOnInit(): void { }
}

