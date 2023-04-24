import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Address } from 'src/app/model/address.model';
import { AddressService } from 'src/app/services/address.service';
import { MyErrorStateMatcher } from '../../register/register.component';

@Component({
  selector: 'app-create-address',
  templateUrl: './create-address.component.html',
  styleUrls: ['./create-address.component.scss']
})
export class CreateAddressComponent implements OnInit {
  constructor(private addressService: AddressService, private route: Router,
    private toast: NgToastService) { }

  aId = 0;
  namef = new FormControl('', [Validators.required]);

  matcher = new MyErrorStateMatcher();

  isChecked = true;
  newAddress: Address = {
    name: ""
  }

  createCategory() {
    this.addressService.createAddress(this.newAddress)
      .subscribe(
        (data) => {
          console.log(data);
          // this.newCategory  = data
          this.toast.success({ detail: "Thông báo thành công", summary: " Đã tạo mới!", duration: 3000 })
          this.route.navigate(['home/address/map']);
        },
        (error) => {
          console.log(error);
          this.toast.error({ detail: "Thông báo lỗi", summary: " Chi nhánh đã tồn tại!", duration: 3000 })
        }
      )
  }
  ngOnInit(): void {

  }
}

