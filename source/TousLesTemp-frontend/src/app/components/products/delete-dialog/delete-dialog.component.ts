import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Route } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.scss']
})
export class DeleteDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
    private productService: ProductsService,
    private toast: NgToastService) { }

  pId: any;

  reciveData() {
    this.pId = this.data.productId;
  }

  deleteProduct() {
    this.productService.deleteProduct(this.pId).subscribe(
      (data) => {
        this.toast.warning({ detail: "Thông báo thành công", summary: " Đã xoá sản phẩm!", duration: 3000 })
      }, (error) => {
        this.toast.error({ detail: "Thông báo lỗi", summary: " Không thể xoá sản phẩm!", duration: 3000 })
      }
    )
  }
  ngOnInit(): void {
    this.reciveData();
  }
}

