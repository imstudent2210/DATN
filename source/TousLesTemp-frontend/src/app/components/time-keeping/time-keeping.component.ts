import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Chart, registerables } from 'chart.js';
import { NgToastService } from 'ng-angular-popup';
import { Store } from 'src/app/model/store.model';
import { StaffService } from 'src/app/services/staff.service';
import { TimeKeepingService } from 'src/app/services/time-keeping.service';
import { environment } from 'src/environment/environment.prod';

@Component({
  selector: 'app-time-keeping',
  templateUrl: './time-keeping.component.html',
  styleUrls: ['./time-keeping.component.scss']
})
export class TimeKeepingComponent implements OnInit {
  constructor(private timeKeepingService: TimeKeepingService, private route: Router,
    private toast: NgToastService, private http: HttpClient, private staffService: StaffService) { }

  @ViewChild('myCanvas') canvas!: ElementRef<HTMLCanvasElement>;
  chart!: Chart;

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  chartData?: any;
  list?: any;
  item?: any;
  timekeeping?: any;
  columns: string[] = ['id', 'staff', 'month', 'createdDate', 'salaryname', 'salaryprice', 'numOfShift', 'edit', 'delete'];
  months = [
    { id: 1, name: 'Tháng Một' },
    { id: 2, name: 'Tháng Hai' },
    { id: 3, name: 'Tháng Ba' },
    { id: 4, name: 'Tháng Tư' },
    { id: 5, name: 'Tháng Năm' },
    { id: 6, name: 'Tháng Sáu' },
    { id: 7, name: 'Tháng Bảy' },
    { id: 8, name: 'Tháng Tám' },
    { id: 9, name: 'Tháng Chín' },
    { id: 10, name: 'Tháng Mười' },
    { id: 11, name: 'Tháng Mười Một' },
    { id: 12, name: 'Tháng Mười Hai' },
  ];
  month=1;
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
  doFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.list.filter = filterValue;
    console.log(filterValue);
  }

  editTimeKeeping(tId: any) {
    this.route.navigate(['/home/timekeeping/update', tId]);
  }
  search() {
    this.getCurrentItem(this.name);
  }

  salaryDetail(sId: any) {
    this.route.navigate(['/home/timekeeping/detail', sId]);
  }

  getAllStaffSalaryPerMonth(month: any) {
    if (this.chart) {
      this.chart.destroy();
    }
    this.http.get(`${environment.API_BASE_URL}/staff-timekeeping/get-all-shiftsalary?month=${month}`).subscribe((result) => {
      this.chartData = result;
      const ctx = this.canvas.nativeElement.getContext('2d') as CanvasRenderingContext2D;
      const labels = this.chartData.map((item: any) => item.name);
      const data = this.chartData.map((item: any) => item.salary);
      this.chart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [{
            label: 'Tiền lương',
            data: data,
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
          }]
        },
        options: {
          plugins: {
            legend: {
              display: true,
              labels: {
                font: {
                  size: 16
                }
              }
            },
            title: {
              display: true,
              text: 'Thống kê lương nhân viên theo tháng',

            }
          },
          responsive: true,
          scales: {
            y: {
              type: 'linear',
              beginAtZero: true
            }
          }
        }
      });
      console.log(this.chartData);
      console.log(labels);

    });
  }

  ngOnDestroy() {
    if (this.chart) {
      this.chart.destroy();
    }
  }

  ngOnInit(): void {
    this.getTimeKeeping();
    Chart.register(...registerables);
    this.getAllStaffSalaryPerMonth(1);
  }
}
