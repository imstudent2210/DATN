import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { CategoriesService } from 'src/app/services/categories.service';
import { Category } from 'src/app/share/category.module';
import { MyErrorStateMatcher } from '../../products/create-product/create-product.component';

@Component({
  selector: 'app-update-category',
  templateUrl: './update-category.component.html',
  styleUrls: ['./update-category.component.scss']
})
export class UpdateCategoryComponent  implements OnInit{

  constructor( private categoriesService:CategoriesService, private route: Router,
               private activatedRoute: ActivatedRoute,private toast: NgToastService){}

  cId = 0;
  namef = new FormControl('', [Validators.required]);
  activatedf = new FormControl('', [Validators.required]);
  matcher = new MyErrorStateMatcher();
  isChecked = true;

  currentCategory: Category = {
    id:0,
    name: "",
    activated:""
  }

  getCurrentCategory(id: number): void {
    this.categoriesService.getCategoryById(id)
      .subscribe(
        data => {
          this.currentCategory = data
        }
      )
  }

  updateCategory(){
    this.categoriesService.updateCategory(this.currentCategory, this.cId)
    .subscribe(
      (data) => {
        console.log(data);
        this.toast.success({ detail: "Thông báo thành công", summary: " Đã cập nhật!", duration: 3000 })
        this.route.navigate(['home/categories/list']);
      },
      (error) => {
        console.log(error);
        this.toast.error({ detail: "Thông báo lỗi", summary: " Cập nhật không thành công!", duration: 3000 })
      }
    )
  }

  ngOnInit(){
    this.cId = this.activatedRoute.snapshot.params['cid'];
    this.getCurrentCategory(this.cId);
  }
}
