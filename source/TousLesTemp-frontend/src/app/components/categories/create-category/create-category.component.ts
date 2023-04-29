import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoriesService } from 'src/app/services/categories.service';
import { Category } from 'src/app/model/category.model';
import { MyErrorStateMatcher } from '../../products/create-product/create-product.component';
import { NgToastService } from 'ng-angular-popup';

@Component({
  selector: 'app-create-category',
  templateUrl: './create-category.component.html',
  styleUrls: ['./create-category.component.scss']
})
export class CreateCategoryComponent implements OnInit {
  constructor(private categoriesService: CategoriesService,
    private route: Router,
    private toast: NgToastService) { }

  cId = 0;
  namef = new FormControl('', [Validators.required]);
  activatedf = new FormControl('', [Validators.required]);
  matcher = new MyErrorStateMatcher();

  isChecked = true;
  newCategory: Category = {
    name: "",
    activated: ""
  }

  createCategory() {
    this.categoriesService.createCategory(this.newCategory)
      .subscribe(
        (data) => {
          console.log(data);
          this.toast.success({ detail: "Thông báo thành công", summary: " Đã tạo mới!", duration: 3000 })
          this.route.navigate(['home/categories/list']);
        },
        (error) => {
          console.log(error);
          this.toast.error({ detail: "Thông báo lỗi", summary: " Tạo mới không thành công!", duration: 3000 })
        }
      )
  }
  ngOnInit(): void { }
}
