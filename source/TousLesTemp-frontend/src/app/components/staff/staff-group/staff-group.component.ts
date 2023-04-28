import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Chart, registerables } from 'chart.js';
import { NgToastService } from 'ng-angular-popup';
import { Store } from 'src/app/model/store.model';
import { StaffGroupService } from 'src/app/services/staffgroup.service';

@Component({
  selector: 'app-staff-group',
  templateUrl: './staff-group.component.html',
  styleUrls: ['./staff-group.component.scss']
})
export class StaffGroupComponent implements OnInit {
  constructor(private staffGroupService: StaffGroupService,
    private route: Router,
    public imageDialog: MatDialog, private toast: NgToastService,
    public deleteDialog: MatDialog) { }

  @ViewChild('myCanvas') canvas!: ElementRef<HTMLCanvasElement>;
  chart!: Chart;
  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  staffNumber?: number;

  gId: any;
  listStaffGroup?: any;
  item?: any;
  staff?: any;
  columns: string[] = ['name', 'activated', 'edit']
  chartData?: any;

  pageSizeOptions = [5, 10];
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }

  editStaffGroup(gId: any) {
    this.route.navigate(["/home/staff/group/update", gId]);
  }
  doFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.staff.filter = filterValue;
    console.log(filterValue);
  }

  getStaffGroup(): void {
    this.staffGroupService.getStaffGroup()
      .subscribe(
        data => {
          this.listStaffGroup = data;
          console.log(this.listStaffGroup.length);
          this.staff = new MatTableDataSource<Store>(this.listStaffGroup);
          this.staff.sort = this.sort;
          this.staff.paginator = this.paginator;
          this.staffNumber = data.length;
        }
      )
  }
  countStaffByGroup() {
    if (this.chart) {
      this.chart.destroy();
    }
    this.staffGroupService.countStaffByGroup().subscribe((result) => {
      this.chartData = result;
      const ctx = this.canvas.nativeElement.getContext('2d') as CanvasRenderingContext2D;
      const labels = this.chartData.map((item: any) => item[0]);
      const data = this.chartData.map((item: any) => item[1]);
      this.chart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [{
            label: 'Số lượng',
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
              text: 'Biểu đồ nhân viên theo chức vụ',

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
  ngOnInit(): void {
    Chart.register(...registerables);
    this.getStaffGroup();
    this.countStaffByGroup();
  }
}

