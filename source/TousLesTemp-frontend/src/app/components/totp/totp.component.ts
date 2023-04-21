import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-totp',
  templateUrl: './totp.component.html',
  styleUrls: ['./totp.component.scss']
})
export class TotpComponent implements OnInit {


  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  currentUser: any;
  userEmail?: string;


  constructor(private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private route: Router,
    private userService: UserService,
    private toast: NgToastService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getUser()) {
      this.isLoggedIn = true;
      this.currentUser = this.tokenStorage.getUser();
    }

  }

  onSubmit(): void {
    this.authService.verify(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.login(data.user);
        this.toast.success({detail:"Xác thực thành công", summary:"Chào mừng đến với trang quản trị!", duration:3000})
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
        this.toast.error({detail:"Lỗi", summary:"Vui lòng nhập lại mã xác thực !", duration:3000})

      }
    );
  }
  value1?: number;
  login(user: any): void {
    this.tokenStorage.saveUser(user);
    this.isLoginFailed = false;
    this.isLoggedIn = true;
    this.currentUser = this.tokenStorage.getUser();
    this.route.navigate(['home/stores/list']);


  }

  otp = new FormControl('', [Validators.required]);
  errror() {
    if (this.otp.hasError('required')) {
      return 'Vui lòng nhập mã OTP';
    }
    return this.otp.hasError('otp') ? 'Không hợp lệ' : '';
  }
}
