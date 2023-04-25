import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Store } from 'src/app/model/store.model';
import { TimeKeepingService } from 'src/app/services/time-keeping.service';

@Component({
  selector: 'app-time-keeping',
  templateUrl: './time-keeping.component.html',
  styleUrls: ['./time-keeping.component.scss']
})
export class TimeKeepingComponent implements OnInit {
  constructor(private timeKeepingService: TimeKeepingService, private route: Router, private toast: NgToastService) { }

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  list?: any;
  item?: any;
  timekeeping?: any;
  columns: string[] = ['id','staff', 'month',  'salaryname','salaryprice','numOfShift','edit', 'delete'];

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
  getTimeKeeping(): void {
    this.timeKeepingService.getTimeKeeping().subscribe((data) => {
      this.timekeeping = data
      console.log(this.timekeeping);
      this.list = new MatTableDataSource<Store>(this.timekeeping);
      this.list.sort = this.sort;
      this.list.paginator = this.paginator;

    });
  }
  deleteTimeKeeping(tId: number) {
    this.timeKeepingService.deleteTimeKeeping(tId).subscribe((data) => {
      this.toast.success({ detail: "Thông báo thành công", summary: " Đã xoá bảng chấm công thành công!", duration: 3000 })
      this.getTimeKeeping();
    },
      (error) => {
        console.log(error);
        this.toast.error({ detail: "Thông báo lỗi", summary: " Không thế xoá bảng chấm công này!", duration: 3000 })
      })
  };

  getCurrentItem(item: any) {
    console.log(item);
  }
  doFilter(event:Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.list.filter = filterValue;
    console.log(filterValue);
  }

  editTimeKeeping(tId: any) {
    this.route.navigate(['/home/timekeeping/update', tId]);
  }
  search(){
    this.getCurrentItem(this.name);
  }


  ngOnInit(): void {
    this.getTimeKeeping();
  }
}
