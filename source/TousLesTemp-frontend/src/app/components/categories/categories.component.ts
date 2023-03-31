import { Component, Input, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CategoriesService } from 'src/app/services/categories.service';
import { Store } from 'src/app/share/store.module';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent {
  constructor(private service: CategoriesService){}

  @ViewChild(MatPaginator) paginator?: MatPaginator;
  @ViewChild(MatSort) sort?: MatSort;
  @Input() name?: any;

  empty?: any;
  item?:any;
  categories?: any;
  columns: string[] = ['id','name','activated']

  pageSizeOptions = [5,3, 10, 25];
  showPageSizeOptions = true;
  showFirstLastButtons = true;
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
  // ================== Call Api BackEnd====================
  getCategories(): void {
    this.service.getCategories().subscribe(
      data => {
        this.empty = data
        console.log(this.empty);
        this.categories = new MatTableDataSource<Store>(this.empty);

        this.categories.sort = this.sort;
        this.categories.paginator = this.paginator;
      }
    )
  }

  getCurrentItem(item: any) {
    console.log(item);
    console.log(item.id +"fffff"+ item.store.name);
  }

  doFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.categories.filter = filterValue;
    console.log(filterValue);
  }

  ngOnInit(): void {
    this.getCategories();
  }
}
