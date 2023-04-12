import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { ImageProcessingService } from 'src/app/services/image-processing.service';
import { ProductsService } from 'src/app/services/products.service';
import { StaffService } from 'src/app/services/staff.service';
import { Staff } from 'src/app/share/staff.module';
import { ImageDialogComponent } from '../products/image-dialog/image-dialog.component';
import { MatTableDataSource } from '@angular/material/table';
import { map } from 'rxjs';
import { Product } from 'src/app/share/product.module';
import { Store } from 'src/app/share/store.module';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.scss']
})
export class StaffComponent implements OnInit {
  constructor( private staffService: StaffService,
    private route: Router,
    public imageDialog: MatDialog,
    public deleteDialog: MatDialog,
    private imageProcessing: ImageProcessingService,){}

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  staffNumber?:number;

  stId: any;
  listStaff?: any;
  item?: any;
  staff?: any;
  columns: string[] = ['name', 'phone', 'email', 'staffGroup', 'store', 'image', 'edit', 'delete']

  pageSizeOptions = [5, 10, 25, 50];
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
  showImages(staff: Staff) {
    console.log(staff);
    this.imageDialog.open(ImageDialogComponent, {
      data: {
        images: staff.images
      },
      height: '400px',
      width: '600px'
    })
  }
  editProduct(stId: any) {
    this.route.navigate(["/home/staff/update", stId]);
  }
  doFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.staff.filter = filterValue;
    console.log(filterValue);
  }
  deleteProduct(stId:number){
    this.deleteDialog.open(DeleteDialogComponent, {
      data: {
        staffId: stId
      }
    }).afterClosed().subscribe(result => {
      this.getStaff();
    })
  }
  getStaff(): void {
    this.staffService.getStaff()
      .pipe(
        map((x: Staff[], i) => x.map((staff: Staff) => this.imageProcessing.createImages(staff)))
      )
      .subscribe(
        data => {
          this.listStaff = data;
          console.log(this.listStaff.length);
          this.staff = new MatTableDataSource<Store>(this.listStaff);
          this.staff.sort = this.sort;
          this.staff.paginator = this.paginator;
          this.staffNumber = data.length;
        }
      )
  }
  ngOnInit(): void {
    this.getStaff();
  }
}
