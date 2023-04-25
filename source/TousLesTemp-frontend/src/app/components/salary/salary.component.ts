import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Store } from 'src/app/model/store.model';
import { SalaryService } from 'src/app/services/salary.service';

@Component({
  selector: 'app-salary',
  templateUrl: './salary.component.html',
  styleUrls: ['./salary.component.scss'],
})
export class SalaryComponent implements OnInit {
  constructor(private service: SalaryService, private route: Router, private toast: NgToastService) {}

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  empty?: any;
  item?: any;
  salary?: any;
  columns: string[] = ['id', 'name', 'salary', 'edit','delete'];

  pageSizeOptions = [3, 5, 10];
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput
        .split(',')
        .map((str) => +str);
    }
  }
  // ================== Call Api BackEnd====================
  getSalary(): void {
    this.service.getSalary().subscribe((data) => {
      this.empty = data;
      console.log(this.empty);
      this.salary = new MatTableDataSource<Store>(this.empty);

      this.salary.sort = this.sort;
      this.salary.paginator = this.paginator;
    });
  }
  deleteSalary(saId: number) {
    this.service.deleteSalary(saId).subscribe((data) => {
      this.toast.success({ detail: "Thông báo thành công", summary: " Đã xoá mức lương thành công!", duration: 3000 })
      this.getSalary();
    });

  }
  getCurrentItem(item: any) {
    console.log(item);
  }

  doFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.salary.filter = filterValue;
    console.log(filterValue);
  }

  editSalary(saId: any) {
    this.route.navigate(['/home/salary/update', saId]);
  }

  ngOnInit(): void {
    this.getSalary();
  }
}
